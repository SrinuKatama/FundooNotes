package com.bridgelabz.fundoonotes.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.dto.RemaindDto;
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
	public  ResponseEntity<Responses>  archievingStatus(@RequestParam Long noteId,@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
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
	/* API For Updating the notes*/
	
	@PutMapping(value = "/updatenote/{token}")
	@ApiOperation(value = "updation of the notes")
	public ResponseEntity<Responses> updateNotes(@RequestBody NotesDto notes,
			@PathVariable String token,@RequestParam Long noteId) throws JWTVerificationException, IllegalArgumentException, Exception
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
	
	/* API For setting  the trash */
	@PutMapping(value = "/Trash_Status/{token}")
	@ApiOperation(value = "Updation of the trash")
	public ResponseEntity<Responses> setTrash( @RequestParam Long noteId,@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long result=noteserve.setTrash(noteId, token);
		if(result==1)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully set to trash", 200, result));
		}
		else if(result==0)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Removed from the trash", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to do the process", 400, result));
		}
		
		
	}
	
	/*API For deleting permanently*/
	
	@PutMapping(value = "/deletenote/{token}")
	@ApiOperation(value = "for deleting the note permanently")
	public ResponseEntity<Responses> deletePermanent(@RequestParam Long noteId, @PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Long result=noteserve.deletePermanent(noteId, token);
		if(result!=null)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully deleted  permanently", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to delete ", 400, result));
		}
		
	}
	/*API for Remainding the note*/
	
	
	@PutMapping(value = "/remindNote/{token}")
	@ApiOperation(value = "For reminding the notes")
	public ResponseEntity<Responses> remind(@RequestBody RemaindDto remindDto,  @RequestParam Long noteId,
			@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Notes result=noteserve.remind(remindDto, noteId, token);
		if(result!=null)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
					body(new Responses("Successfully remaind the notes", 200, result));
		}
		else
		{

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to remaind the notes ", 400, result));
		}
		
	}
	
	/* For getting all user by id*/
	
	
	@GetMapping(value = "/listofnotes/{token}")
	@ApiOperation(value = "For  getting list of notes")
	public ResponseEntity<Responses> getListOfNotes(@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		
		List<Notes> result=noteserve.getListOfNotes(token);
		if(result!=null)
		{
			return  ResponseEntity.status(HttpStatus.ACCEPTED).
				body(new Responses("Successfully fetching the data", 200, result));
			
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to get the data ", 400, result));
			
		}
		
	}
	
	/*API for getting listofnotes byname*/
	
	@GetMapping(value = "/sortbyname/{token}")
	@ApiOperation(value = "listofnotes_byname")
	public ResponseEntity<Responses> sortByName(@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		 List<Notes>  result=noteserve.sortByName(token);
		 if(result!=null)
		 {
			 return  ResponseEntity.status(HttpStatus.ACCEPTED).
						body(new Responses("Successfully fetching the data byname", 200, result));
		 }
		 else
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).
						body(new Responses("unable to get the data ", 400, result));
				
			 
		 }
		
	}
	
	
	/*API for gettinglist by dateandtime*/
	@GetMapping(value = "/sortbydateandtime/{token}")
	public  ResponseEntity<Responses> sortByDate(@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		List<LocalDateTime> result=noteserve.sortByDate(token);
		if(result!=null)
		{
			 return  ResponseEntity.status(HttpStatus.ACCEPTED).
						body(new Responses("Successfully fetching the data byname", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to get the data ", 400, result));
		}
	}
	
	/* API for gettinglist by Archivenotes*/
	
	@GetMapping(value = "/allrchiveNotes/{token}")
	@ApiOperation(value = "for getting all archives")
	public ResponseEntity<Responses> getAllarchieveNotes(@PathVariable String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		 List<Notes> result=noteserve.getAllarchieveNotes(token);
		 if(result!=null)
		 {
			 return  ResponseEntity.status(HttpStatus.ACCEPTED).
						body(new Responses("Successfully fetching the data byname", 200, result));
		 }
		 else
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).
						body(new Responses("unable to get the data ", 400, result));
		 }
		
	}
	
	/* API for search by id*/
	
	@GetMapping(value = "/searchbyid/")
	public ResponseEntity<Responses> searchById(@RequestParam Long noteId)
	{
		
		Notes result=noteserve.searchById(noteId);
		if(result!=null)
		{
			 return  ResponseEntity.status(HttpStatus.ACCEPTED).
						body(new Responses("Successfully fetching the data byname", 200, result));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).
					body(new Responses("unable to get the data ", 400, result));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
