package com.bridgelabz.fundoonotes.controller;


/* Author :Srinu */


import java.io.UnsupportedEncodingException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.ResetPassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.UserServe;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController 
{

	@Autowired
	private UserServe UserService;

	
	
	/* API for Registration purpose */

	@PostMapping(value = "/registration")
	@ApiOperation(value = "registration for new user")
	public ResponseEntity<Responses> forRegistration(@RequestBody UserDto user) throws Exception {
		System.out.println(user.getUseremail());
		UserDetails result = UserService.register(user);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Responses("successfully registerd", 200, result));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("user already exist", 400, result));
		}

	}
	
	
	/* API for verification purpose*/
	
	
	@GetMapping("/mailVerification")
	@ApiOperation(value="for verification purpose")
	public ResponseEntity<Responses> jwt(@PathVariable String token)
	{
		UserDetails result=UserService.mailVerification(token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully verified",
							200, result));
				
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("not verified", 400, result));
		}
		
	}
	
	/* API for Login */
	
	
	@PostMapping("/login")
	@ApiOperation(value="for login purpose")
	public ResponseEntity<Responses> forLonin(@RequestBody LoginDto details) throws UnsupportedEncodingException
	{
		UserDetails result=UserService.login(details);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully loged in", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("Unable to login", 400, result));
			
		}
		
		
	}
	
	
	/*   Forget password */
	
	@PostMapping("/forgetPasswod")
	@ApiOperation(value="for forgotten password")
	public ResponseEntity<Responses> forgetPassword(@PathVariable String email) throws UnsupportedEncodingException
	{
		
		UserDetails result=UserService.forgetPasswod(email);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully send the link", 200, result));
			
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("Unable to process", 400, result));
		}
		
		
	}
	
	/* Update Password  */
	
	@PostMapping("/updatePassword")
	@ApiOperation(value="for updation of the password")
	public ResponseEntity<Responses> updatePassword(@RequestBody ResetPassword password
			,@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		boolean result=UserService.updatePassword(password, token);
		if(result)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully updation done", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("Unable to updating the password", 400, result));
		}
		
		
	}
	
	/* for getting all users information */
	
	@GetMapping("/getAllusers")
	@ApiOperation(value="for getting all users infoprmation")
	public ResponseEntity<Responses> getAllusers(String str)
	{
		List<UserDetails> result=UserService.getAllusers(str);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully all users data fetched", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("Unable to updating the password", 400, result));
			
		}
		
	}
	

}
