package com.bridgelabz.fundoonotes.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.model.UserDetails;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utility.JWTutil;



@Service
public class NoteServiceImp implements NoteService
{
	
	@Autowired
	private NotesRepository notesrepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private JWTutil jwt;
	
	Notes note=new Notes();
	
	LocalDate currentDate = LocalDate.now();
	LocalTime currentTime = LocalTime.now();

	LocalDateTime dateTime = LocalDateTime.of(currentDate, currentTime);

	
	/* For adding the notes */

	@Override
	public Notes addNotes(NotesDto notes, String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		
		if(userdetails!=null)
		{
			note.setNoteTitle(notes.getNoteTitle());
			note.setContent(note.getContent());
			note.setUser(userdetails);
			note.setPin(false);
			
			notesrepo.addNotes(note.getNoteTitle(), note.getContent(), dateTime, note.getNoteid());
			Notes fectchingnotes=notesrepo.selectLastNotes();
			return fectchingnotes;
			
		}
		
		
		
		
		return null;
	}




	@Override
	public boolean changeColor(String color, Long noteid, String token) throws Exception, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		if(userdetails!=null)
		{
		Notes note1 =notesrepo.findById(noteid);
		}
		return false;
	}

}
