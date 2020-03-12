package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.ResetPassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;



public interface UserServe
{
	// For login and Registration we use this methods //
	
	UserDetails save(UserDto user);
//	UserDetails login(LoginDto login);
//	UserDetails forgetPasswod(String email);
//	UserDetails mailVerification(String token);
//	boolean updatePassword(ResetPassword password,String token);
//	List<UserDetails> getAllusers(String str);
	
}
