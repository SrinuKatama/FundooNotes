package com.bridgelabz.fundoonotes.controller;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.UserServe;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	private UserServe UserService;

	private Validator Validator;
	
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responses("user already exist", 400, result));
		}

	}

}
