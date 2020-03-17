package com.bridgelabz.fundoonotes.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.dto.RemaindDto;
import com.bridgelabz.fundoonotes.model.Notes;
@Service
public interface NoteService
{
	 Notes addNotes(NotesDto notes,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	 boolean changeColor(String color,Long noteid,String token) throws Exception, IllegalArgumentException, Exception;
	 Integer changingPin(Long noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	 Integer archievingStatus(Long  noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	 Long updateNotes(NotesDto notes,String token,Long  noteId) throws IOException, JWTVerificationException, IllegalArgumentException, Exception;
     Integer setTrash(Long noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
     Long deletePermanent(Long noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception;
     Notes remind(RemaindDto  remindDto,Long noteId,String token) throws JWTVerificationException, IllegalArgumentException, Exception; 
	 List<Notes> getListOfNotes(String token) throws JWTVerificationException, IllegalArgumentException, Exception; 
	 List<Notes> sortByName(String token) throws JWTVerificationException, IllegalArgumentException, Exception; 
	 List<LocalDateTime> sortByDate(String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	 List<Notes> getAllarchieveNotes(String token) throws JWTVerificationException, IllegalArgumentException, Exception;
	 Notes searchById(Long noteId);
	 
	 

}
