package com.smsapi.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.constant.TopupStatus;
import com.smsapi.dao.CreditDao;
import com.smsapi.dao.CreditDaoUpdate;
import com.smsapi.dao.TopupDao;
import com.smsapi.dao.TopupDaoUpdate;
import com.smsapi.dao.TopupTaskDaoUpdate;
import com.smsapi.dao.UserDao;
import com.smsapi.model.CreditModel;
import com.smsapi.model.TopupModel;
import com.smsapi.model.TopupTaskModel;


@Service
@Transactional
public class TopupTask {

	@Autowired
	private TopupDao topupDao;
	
	@Autowired
	private CreditDao creditDao;
	
	@Autowired
	private TopupTaskDaoUpdate topuptaskDaoUpdate;
	
	@Scheduled(fixedDelay=1000)
	public void doProcess() {
	    
		List<TopupModel> topuplist=topupDao.findByStatusEquals(TopupStatus.INITIATED);


		
		for(int i=0;i<topuplist.size();i++) {
			
			System.out.println("Task");
			TopupModel topup=topuplist.get(i);			
			topup.setModifiedDateTime();
			
			
			CreditModel topupuser=creditDao.findByUsernameEquals(topup.getUsername());

			topupuser.setTotalbalance(topupuser.getTotalbalance()+topup.getTopupvalue());
			
			TopupTaskModel entity=new TopupTaskModel();
			entity.setTopupmodel(topup);
			entity.setCreditmodel(topupuser);
			topup.setStatus(TopupStatus.DONE);
			topuptaskDaoUpdate.save(entity);
			
		}
	}
}
