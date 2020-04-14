package com.bridgelabz.fundoonotes.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.dto.RemaindDto;
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
	public Notes addNotes(NotesDto notes, String token)
			throws JWTVerificationException, IllegalArgumentException, Exception {
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);

		if (userdetails != null) {
			note.setNoteTitle(notes.getNoteTitle());
			note.setContent(note.getContent());
			note.setUser(userdetails);
			note.setPin(false);

			notesrepo.addNotes(note.getNoteTitle(), note.getContent(), dateTime, note.getNoteid());
			Notes fectchingnotes = notesrepo.selectLastNotes();
			return fectchingnotes;

		}

		return null;
	}
	
	/* for changing the color */

	@Override
	public boolean changeColor(String color, Long noteid, String token)
			throws Exception, IllegalArgumentException, Exception {
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);

		long id = userdetails.getId();
		if (notesrepo.findByNoteId(noteid)!=null) {
			notesrepo.updateColor(color, noteid, id);
			return true;

		} else {
			return false;
		}

	}

	
	// for updating the pin status
	@Override
	public Integer changingPin(Long noteId, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		if(notesrepo.findByNoteId(noteId)==null)
		{
			notesrepo.updatePin(false, noteId, id);
			return 1;
		}
		else if(notesrepo.findByNoteId(noteId)!=null)
		{
			notesrepo.updatePin(true, noteId, id);
			return 0;
		}
		else
		{
			return -1;
		}
		
	}
	
	
	//For archive status

	@Override
	public Integer archievingStatus(Long noteId, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		
		if(notesrepo.findByNoteId(noteId)==null)
		{
			notesrepo.updateArchive(false, noteId, id);
			return 1;
		}
		else if(notesrepo.findByNoteId(noteId)!=null)
		{
			return 0;
		}
		else
		{
			return -1;
		}
		
	}
	
	
	// For updating the Note title and content

	@Override
	public Long updateNotes(NotesDto notes, String token, Long noteId) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		
		if(notesrepo.findByNoteId(noteId)!=null)
		{
			note.setNoteTitle(notes.getNoteTitle());
			note.setNoteTitle(notes.getNoteTitle());
			notesrepo.updateNotes(note.getNoteTitle(), note.getContent(), noteId, id);
			return noteId;
		}
		
		return null;
	}

	// For update deleting or update Trash the note
	
	@Override
	public Integer setTrash(Long noteId, String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		Notes note =notesrepo.findByNoteId(noteId);
		if(note.isTrash())
		{
			notesrepo.updateTrash(false, noteId, id);
			notesrepo.updatePin(false, noteId, id);
			return 1;
		}
		else if(!note.isTrash())
		{
			notesrepo.updateTrash(true, noteId, id);
			notesrepo.updatePin(false, noteId, id);
			return 0;
		}
		return -1;
		
	}

	// Delete  note permanently from trash
	
	@Override
	public Long deletePermanent(Long noteId, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		Notes note1 =notesrepo.findByNoteId(noteId);
		if(note1!=null)
		{
			if(note.isTrash())
			{
				notesrepo.deleteNotes(noteId, id);
				return noteId;

			}
			
		}
		
		return null;
	}

	
	// For Reminding the notes
	@Override
	public Notes remind(RemaindDto remindDto, Long noteId, String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid=jwt.parseJWT(token);
		UserDetails userdetails=userRepo.findById(tokenid);
		long id=userdetails.getId();
		Notes note1 =notesrepo.findByNoteId(noteId);
		if(note1!=null)
		{
			note.setRemaindAt(remindDto.getLocaldatetime());
			note.setRemaindMe(remindDto.getPlace());
			notesrepo.remaind(note1.getRemaindAt(), note1.getRemaindMe(), noteId, id);
			return note;
			
		}
		
		return null;
	}
	
	

	// For fetching all notes of the user
	@Override
	public List<Notes> getListOfNotes(String token)
			throws JWTVerificationException, IllegalArgumentException, Exception {
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);
		long uid=userdetails.getId();
		Notes note1 = notesrepo.findByNoteId(uid);
		if (userdetails.getId() != null) {
			return notesrepo.getListOfNotes(note1.getNoteid(), userdetails.getId());

		}

		return null;
	}

	// For fetching all notes of the user by name 
	@Override
	public List<Notes> sortByName(String token) throws JWTVerificationException, IllegalArgumentException, Exception 
	{
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);
		
		
		Long uid=userdetails.getId();
		
		if(uid != null)
		{
			List<Notes> note2=notesrepo.getAllNotesByName(uid);
			note2.parallelStream().sorted(Comparator.comparing(Notes :: getNoteTitle)).
			collect(Collectors.toList());
			return note2;
		}
		
		return null;
	}

	
	//  Fetching the list by sortby datetime
	@Override
	public List<LocalDateTime> sortByDate(String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);
		Long uid=userdetails.getId();
		if(uid!=null)
		{
			List<Notes> note2=notesrepo.getListOfNotesbyid(uid);
			note2.parallelStream().sorted(Comparator.comparing(Notes::getRemaindAt)).collect(Collectors.toList());
		}
		
		return null;
	}
	
	//Fetching the list of notes which are archive

	@Override
	public List<Notes> getAllarchieveNotes(String token) throws JWTVerificationException, IllegalArgumentException, Exception
	{
		long tokenid = jwt.parseJWT(token);
		UserDetails userdetails = userRepo.findById(tokenid);
		Long uid=userdetails.getId();
		if(uid!=null)
		{
			List<Notes> notes2=notesrepo.getAllNotesByArchive(uid);
			notes2.parallelStream().sorted(Comparator.comparing(Notes::isArchive)).collect(Collectors.toList());
		}
		return null;
	}

	
	// For fetching by using id
	@Override
	public Notes searchById(Long noteId) 
	{
		Notes note2=notesrepo.findByNoteId(noteId);
		if(note2!=null)
		{
			String searchId=String.valueOf(note2.getNoteid());
			return note2;
		}

		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
