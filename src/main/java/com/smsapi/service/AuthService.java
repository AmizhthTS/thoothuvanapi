package com.smsapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsapi.dao.UserDao;
import com.smsapi.dao.UserDaoUpdate;
import com.smsapi.jwt.JwtTokenUtil;
import com.smsapi.model.ChangePasswordModel;
import com.smsapi.model.UserModel;

import exception.AdminUserNotAvailable;
import exception.CreateUserNotAdmin;
import exception.PasswordMissMatch;
import exception.UsernameExsist;
import exception.WrongPassword;

@Service
@Transactional
public class AuthService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoUpdate userUpdateDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	public UserModel login(UserModel userDTO)  {
		System.out.println("AuthService.login  (userDTO.getUserName() : "+userDTO.getUsername()+ " userDTO.getPassword() : "+userDTO.getPassword() );
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

		UserModel authModel = userDao.findByUsernameEquals(userDTO.getUsername());
		if (authModel != null) {
			
			if(authModel.getPassword().equals(userDTO.getPassword())) {
				
				
				if(authModel.getStatus()==1) {
				

					UserModel retAuthDTO =authModel;
					
					retAuthDTO.setToken(jwtTokenUtil.generateToken(authModel.getUsername()));
					retAuthDTO.setStatus(1);
					retAuthDTO.setId(authModel.getId());
				
					return retAuthDTO;
				
				}else {
					
					throw new DisabledException("Account Disabled");
					
				}	
				
			}else {
				
				throw new BadCredentialsException("Password Not Matched ");
			}
		}else {
			
			throw new UsernameNotFoundException("Username Not Found : "+userDTO.getUsername());
		}
		
				
	}


}