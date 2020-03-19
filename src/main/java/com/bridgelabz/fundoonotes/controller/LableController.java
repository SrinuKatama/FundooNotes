package com.bridgelabz.fundoonotes.controller;
/* Author : Srinu*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.LableDto;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.LableService;

import io.swagger.annotations.ApiOperation;




@RestController
public class LableController 
{ 
	@Autowired
	private LableService lableserve;
	
	/* Api for creating the lable*/
	
	@PostMapping("/creatinglable/{token}")
	@ApiOperation(value = "for creating the lable")
	public ResponseEntity<Responses> createLable(@RequestBody LableDto LableDto,
			@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Lable result=lableserve.createLable(LableDto, token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully lable created", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to create lable", 400, result));
		}
		
		
	}
	
	/* API for update lable*/
	@PostMapping("/updatelable/{token}")
	@ApiOperation("for updating the lable")
	public ResponseEntity<Responses> updateLable(@RequestParam Long id,@PathVariable String token,
			@RequestParam String lableName) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Long result=lableserve.updateLable(id, token, lableName);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully updatedlable", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to updatelable", 400, result));
		}
		
	}
	
	/*Api for delete lable*/
	
	@DeleteMapping("/deletelable/{token}")
	@ApiOperation(value = "for deleting the lable")
	public ResponseEntity<Responses> deleteLable(@RequestParam Long lableid,@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Long result=lableserve.deleteLable(lableid, token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully deleted the lable", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to delete lable", 400, result));
		}
		
		
	}
	/* Mapping notetable and labletable*/
	
	@PostMapping("/mappingwithnote")
	@ApiOperation("mapping notetable & labletable")
	public ResponseEntity<Responses> mapWithNote(@PathVariable String token,@RequestParam Long noteid,
			@RequestParam String lableName) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Lable result=lableserve.mapWithNote(token, noteid, lableName);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully two tables are mapped", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to mapping", 400, result));
		}
		
	}
	
	/*Api for getting the allthelables for one user*/
	
	@GetMapping("/getusers")
	@ApiOperation("getting all the lables of the user")
	public ResponseEntity<Responses> getAllusers(String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		List<Lable> result=lableserve.getAllusers(token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("successfully getting the lables", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to the list", 400, result));
		}
		
	}
	

}
