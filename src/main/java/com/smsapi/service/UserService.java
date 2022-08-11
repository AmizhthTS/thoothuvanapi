package com.smsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.dao.OldPasswordDao;
import com.smsapi.dao.UserDao;
import com.smsapi.dao.UserDaoUpdate;
import com.smsapi.exception.AdminUserNotAvailable;
import com.smsapi.exception.CreateUserNotAdmin;
import com.smsapi.exception.PasswordMissMatch;
import com.smsapi.exception.UsernameExsist;
import com.smsapi.exception.WrongPassword;
import com.smsapi.model.ChangePasswordModel;
import com.smsapi.model.UserModel;

@Service
@Transactional
public class UserService {


	@Autowired
	private OldPasswordDao oldpasswordDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoUpdate userUpdateDao;
	
	public UserModel passwordchange(ChangePasswordModel passwordDTO)   {


		if(!passwordDTO.getChangepassword().equals(passwordDTO.getConfirmpassword())) {
			
			throw new PasswordMissMatch("Passsword Mismatch");
		}
		
		
		UserModel authModel = userDao.findByUsernameEquals(passwordDTO.getUsername());
		
		if (authModel != null) {
			
			if(authModel.getPassword().equals(passwordDTO.getPassword())) {
				
				
				authModel.setPassword(passwordDTO.getChangepassword());
				
				authModel.setPasswordactivation(0);
				
				oldpasswordDao.save(authModel.getOldPasswordModel());
				
				userDao.saveAndFlush(authModel);
				
				return authModel;
				
				
				
			}else {
				
				throw new WrongPassword("Wrong Password");
			}
		}else {
			
			throw new UsernameNotFoundException("Username Not Found : "+passwordDTO.getUsername());
		}
		
				
	}
	
	
	
	public UserModel createUser(UserModel createDTO)   {

		UserModel authModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(authModel!=null) {
			
			throw new UsernameExsist("Username Already Exsists");
		}else {
			
			authModel = userDao.findByUsernameEquals(createDTO.getAdmin());
			if(authModel!=null) {
				
			if(authModel.getRole().equals("user")) {
				
				throw new CreateUserNotAdmin("Creating User is not Admin");
			}
			
			userDao.saveAndFlush(createDTO);
			
			return userDao.findByUsernameEquals(createDTO.getUsername());

			
			}else {
				
				throw new AdminUserNotAvailable("Admin User not Available");

				
			}

		}
						
	}


	
	public UserModel editUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(userModel==null) {
			
			throw new UsernameExsist("UsernameNotExsists");
			
		}else {
			
			UserModel authModel = userDao.findByUsernameEquals(createDTO.getAdmin());
			if(authModel!=null) {
				
			if(authModel.getRole().equals("user")) {
				
				throw new CreateUserNotAdmin("Creating User is not Admin");
			}
			
			userModel.setRole(createDTO.getRole());
			
			userModel.setBilltype(createDTO.getBilltype());

			userModel.setProfitpercentage(createDTO.getProfitpercentage());
			
			userModel.setStatus(createDTO.getStatus());
			
			userModel.setModifiedDateTime();
			
			userModel.setPassword(createDTO.getPassword());
			
			
			userUpdateDao.save(userModel);
			
			return userDao.findByUsernameEquals(createDTO.getUsername());

			
			}else {
				
				throw new AdminUserNotAvailable("Admin User not Available");

				
			}

		}
						
	}
	
	
	public UserModel getUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		
		return userModel;
						
	}
	
	
	
	public List<UserModel> listUser(UserModel createDTO)   {

		UserModel userModel = userDao.findByUsernameEquals(createDTO.getUsername());
		
		if(userModel==null) {
			
			throw new UsernameExsist("UsernameNotExsists");
			
		}else {
			
			List<UserModel> authModel = userDao.findByAdminEquals(createDTO.getUsername());
			
			if(authModel!=null&&authModel.size()>0) {
				
				return authModel;

			
			}else {
				
				throw new AdminUserNotAvailable("Admin User not Available");

				
			}

		}
						
	}



}