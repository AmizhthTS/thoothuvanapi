package com.smsapi.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import exception.AdminUserNotAvailable;
import exception.CreateUserNotAdmin;
import exception.PasswordMissMatch;
import exception.UsernameExsist;
import exception.UsernameNotExsist;
import exception.WrongPassword;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		if(authException instanceof UsernameNotFoundException) {
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credential");

		}else if(authException instanceof BadCredentialsException){
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credential");

		}else if(authException instanceof DisabledException){
			
			response.sendError(HttpServletResponse.SC_MOVED_TEMPORARILY, "Account Disabled");

		}else if(authException instanceof PasswordMissMatch){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "PasswordMissMatch");

		}else if(authException instanceof WrongPassword){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "WrongPassword");

		}else if(authException instanceof UsernameExsist){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "UsernameExsists");

		}else if(authException instanceof CreateUserNotAdmin){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "CreateUserNotAdmin");

		}else if(authException instanceof AdminUserNotAvailable){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "AdminUserNotAvailable");

		}else if(authException instanceof UsernameNotExsist){
			
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "UsernameNotExsist");

		}else {
			
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	
		}
	}
}
