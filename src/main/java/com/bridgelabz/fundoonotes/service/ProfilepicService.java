package com.bridgelabz.fundoonotes.service;

import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.model.ProfilePic;

public interface ProfilepicService 
{

	ProfilePic uploadProfilePic(MultipartFile file,String fileName,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	
	boolean deleteFileName(Long profileId);

}
