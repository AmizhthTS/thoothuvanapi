package com.smsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.ChangePasswordModel;
import com.smsapi.model.UserModel;
import com.smsapi.service.AuthService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private AuthService authService;

	
	@PostMapping("/changepassword")
	public ResponseEntity<?> changepassword(@RequestBody ChangePasswordModel authDTO) {

		UserModel authOTPVerifyResponseModel = authService.passwordchange(authDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserModel createDTO) {

		UserModel authOTPVerifyResponseModel = authService.createUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/edit")
	public ResponseEntity<?> editUser(@RequestBody UserModel createDTO) {

		UserModel authOTPVerifyResponseModel = authService.editUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/list")
	public ResponseEntity<?> listUser(@RequestBody UserModel createDTO) {

		List<UserModel> authOTPVerifyResponseModel = authService.listUser(createDTO);
		
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
}