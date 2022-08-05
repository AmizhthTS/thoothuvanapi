package com.smsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.constant.Role;
import com.smsapi.dao.CreditDao;
import com.smsapi.dao.TopupDao;
import com.smsapi.dao.UserDao;
import com.smsapi.model.CreditModel;
import com.smsapi.model.TopupModel;
import com.smsapi.model.UserModel;

import exception.AdminNameMissMatched;
import exception.BilltypeNotPrepaid;
import exception.UserNotRegisteredForCredit;
import exception.UsernameNotExsist;

@Service
@Transactional
public class CreditService {

	@Autowired
	private TopupDao topupDao;

	@Autowired
	private CreditDao creditDao;
	

	@Autowired
	private UserDao userDao;
	
	
	
	
	public CreditModel addUser(CreditModel createDTO)   {

		UserModel authModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel==null) {
			
			throw new UsernameNotExsist("Username Not Exsist");
			
		}else {
			
	
			if(!authModel.getBilltype().equals("prepaid")) {
				
				throw new BilltypeNotPrepaid("User not prepaid Bill Type");
			}
			
			if(!authModel.getAdmin().equals(createDTO.getAdmin())) {
				
				throw new AdminNameMissMatched("Admin Name Miss Matched");
			}
			
			
			CreditModel credituser = creditDao.findByUsernameEquals(createDTO.getAdmin());
			
			if(credituser==null) {
				
				UserModel admin=userDao.findByUsernameEquals(createDTO.getAdmin());
				
				
				if(admin==null||admin.getRole().equals(Role.CUSTOMER)||admin.getRole().equals(Role.ADMIN)||admin.getRole().equals(Role.USER)) {
					
					throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");

				}
			}

			creditDao.saveAndFlush(createDTO);
			
			return creditDao.findByUsernameEquals(createDTO.getUsername());

			
			

		}
						
	}
	
	
	
	public List<TopupModel> topupCredit(TopupModel createDTO)   {

		CreditModel authModel = creditDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel==null) {
			
			throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");
			
		}else {
			
	
			
			if(!authModel.getAdmin().equals(createDTO.getAdmin())) {
				
				throw new AdminNameMissMatched("Admin Name Miss Matched");
			}
			
			
			
			authModel = creditDao.findByUsernameEquals(createDTO.getAdmin());
			
			if(authModel==null) {
				
				UserModel admin=userDao.findByUsernameEquals(createDTO.getAdmin());
				
				
				if(admin.getRole().equals(Role.CUSTOMER)||admin.getRole().equals(Role.ADMIN)||admin.getRole().equals(Role.USER)) {
					
					throw new UserNotRegisteredForCredit("UserNotRegisteredForCredit");

				}
			}
			
			topupDao.saveAndFlush(createDTO);
			
			return topupDao.findByAdminEquals(createDTO.getAdmin());

			
			

		}
						
	}



}