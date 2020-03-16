package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.responses.Responses;
import com.bridgelabz.fundoonotes.service.NoteService;

import io.swagger.annotations.ApiOperation;


@RestController
public class NotesController 
{
	
	@Autowired
	private NoteService noteserve;
	
	/* For Creation of new Note */
	
	@PostMapping(value="/addNotes or Createnote/{token}")
	@ApiOperation(value = "adding the new notes")
	public ResponseEntity<Responses> addNotes(@RequestBody NotesDto notes, @PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Notes result=noteserve.addNotes(notes, token);
		if(result!=null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Created Successfully", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("Creation of note failed", 400, result));
		}
	
	}
	
	/* For changing the color */
	
	@PutMapping(value="/ChangeColor/{token}")
	@ApiOperation(value = "changing the color of note")
	public ResponseEntity<Responses> changeColor(@RequestParam String color,
			@RequestParam Long noteid,@PathVariable String token) throws IllegalArgumentException, Exception
	{
		boolean result=noteserve.changeColor(color, noteid, token);
		if(result)
		{
		return  ResponseEntity.status(HttpStatus.ACCEPTED).
				body(new Responses(" Successfully Color changed", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to change the color", 400, result));
		
		}
		
	}
	
	/* For changing the pin status*/
	
	@PutMapping(value="/PinStatus/{token}")
	@ApiOperation(value = "changing the pin status")
	public ResponseEntity<Responses> changingPin(@RequestParam Long noteId,
			@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long result=noteserve.changingPin(noteId, token);
		if(result==1)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully pin changed", 200, result));
		}
		else if(result==0)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully pin changed", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to change the pin", 400, result));
			
		}
		
	}
	
	/* For changing the archive status*/
	
	@PutMapping(value = "/archivestatus/{tocken}")
	@ApiOperation(value = "for changing archive status")
	public  ResponseEntity<Responses>  archievingStatus(Long  noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long result=noteserve.archievingStatus(noteId, token);
		if(result==1)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully archive status changed", 200, result));
		}
		else if(result==0)
		{	return  ResponseEntity.status(HttpStatus.ACCEPTED).
				body(new Responses("Successfully archive status changed", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to change the status of archive", 400, result));

		}
		
	}
	/* For Updating the notes*/
	
	
	@PutMapping(value = "/updatenote/{token}")
	@ApiOperation(value = "updation of the notes")
	public ResponseEntity<Responses> updateNotes(NotesDto notes,String token,Long  noteId) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long result=noteserve.updateNotes(notes, token, noteId);
		if(result==1)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully archive status changed", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to updates the notes", 400, result));
		}
		
		
	}
	
	
	

}
