package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lable;

public interface LableService 
{
	Lable createLable(LableDto LableDto, String token)
			throws JWTVerificationException, IllegalArgumentException, Exception;

	Long updateLable(Long id, String token, String lableName)
			throws JWTVerificationException, IllegalArgumentException, Exception;

	Long deleteLable(Long lableid, String token) throws JWTVerificationException, IllegalArgumentException, Exception;

	Lable mapWithNote(String token, Long noteid, String lableName) throws JWTVerificationException, IllegalArgumentException, Exception;

	List<Lable> getAllusers(String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	
}
