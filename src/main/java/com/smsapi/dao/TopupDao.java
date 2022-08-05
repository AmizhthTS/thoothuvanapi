package com.smsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.TopupModel;


@Repository
public interface TopupDao extends JpaRepository<TopupModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	TopupModel saveAndFlush(TopupModel entity);
	
	List<TopupModel> findByAdminEquals(String admin);
	
	List<TopupModel> findByUsernameEquals(String username);

	List<TopupModel> findByStatusEquals(String status);

}