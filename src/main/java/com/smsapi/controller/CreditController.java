package com.smsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsapi.model.CreditModel;
import com.smsapi.model.TopupModel;
import com.smsapi.service.CreditService;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

		
	@PostMapping("/adduser")
	public ResponseEntity<?> createUser(@RequestBody CreditModel createDTO) {

		CreditModel authOTPVerifyResponseModel = creditService.addUser(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	@PostMapping("/topup")
	public ResponseEntity<?> topup(@RequestBody TopupModel createDTO) {

		List<TopupModel> authOTPVerifyResponseModel = creditService.topupCredit(createDTO);
		return ResponseEntity.ok().body(authOTPVerifyResponseModel);
	
	}
	
	
	
	
}