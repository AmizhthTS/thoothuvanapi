package com.smsapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.model.TopupTaskModel;

@Repository 
public interface TopupTaskDaoUpdate extends CrudRepository<TopupTaskModel, Integer> {

}