package com.bridgelabz.fundoonotes.service;

/* Author :Srinu */

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.ResetPassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;



public interface UserServe
{
	// For login and Registration we use this methods //
	
	UserDetails register(UserDto user) throws Exception;
	UserDetails login(LoginDto login) throws UnsupportedEncodingException;
	UserDetails forgetPasswod(String email) throws UnsupportedEncodingException;
	UserDetails mailVerification(String token);
    boolean updatePassword(ResetPassword password,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
    List<UserDetails> getAllusers(String str);
	
}
