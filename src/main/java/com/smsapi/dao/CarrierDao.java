package com.smsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.smsapi.model.CarrierModel;
import com.smsapi.model.UserModel;


@Repository
public interface CarrierDao extends JpaRepository<CarrierModel, Integer> {

	@SuppressWarnings("unchecked")
	@Modifying(flushAutomatically = true)
	CarrierModel saveAndFlush(CarrierModel entity);
	
	CarrierModel findByCarriernameEquals(String carriername);
	

}