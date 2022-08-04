package com.smsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.dao.UserDao;
import com.smsapi.model.UserModel;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao authDao;

	public UserModel registerNewUserAccount(UserModel usermodel)   {
	    if (authDao.findByUsernameEquals(usermodel.getUsername())!=null) {
	        throw new UsernameNotFoundException(
	          "There is an account with that username :" + usermodel.getUsername());
	    }
	    return authDao.saveAndFlush(usermodel);
	}

}