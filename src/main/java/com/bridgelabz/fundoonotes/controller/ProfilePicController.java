package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.model.ProfilePic;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.ProfilepicService;

@RestController
public class ProfilePicController 
{
	
	@Autowired
	private ProfilepicService profilepicser;
	
	/* API for uploading the profilepicture */
	
	
	@PostMapping("/uploadpic/{token}")
	public ResponseEntity<Responses> uploadpic(@ModelAttribute MultipartFile multipartFile,
			@RequestParam String fileName, @PathVariable("token") String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		ProfilePic result=profilepicser.uploadProfilePic(multipartFile, fileName, token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responses("successfully uploaded", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responses("unable to upload", 400, result));
		}
		
	}
	
	/* API for Deleting the Picture */

	@DeleteMapping("/delete-profilePic")
	public ResponseEntity<Responses> deletepic(@RequestParam Long profileId)
	{
		boolean result=profilepicser.deleteFileName(profileId);
		if(result)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Responses("successfully deleted", 200, result));

		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responses("unable to delete", 400, result));

		}
		
	}

}
