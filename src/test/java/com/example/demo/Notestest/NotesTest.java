package com.example.demo.Notestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.camel.language.Bean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.fundoonotes.dto.NotesDto;
import com.bridgelabz.fundoonotes.model.Notes;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.service.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotesTest 
{
	@MockBean
	NotesRepository notesrepo;
	
	@Autowired
	NoteService notesserve;
	
	@Test
	public void addNotesTest() throws JWTVerificationException, IllegalArgumentException, Exception
	{
		Notes notes=new Notes("sri","skhsbkbdkbd",'10:15:30',1);
                                                                
		String token="adhiufufhsdddiuh";
		NotesDto notesdto=new NotesDto();
		when(notesserve.addNotes(notesdto, token)).thenReturn(notes);
		assertEquals(1, notesrepo.addNotes(notes.getNoteTitle(), notes.getContent(), createdtime, notes.getNoteid()););
	}
	
	@Test
	public void changecolortest()
	{
		String color="#fff";
		NotesDto notesdto=new NotesDto();
		Notes notes=new Notes("sri","skhsbkbdkbd",'10:15:30',1);
		String token="adhiufufhsdddiuh";
		when(notesserve.changeColor(color, notes.getNoteid(), token)).thenReturn(notes);
		assertEquals(1,notesrepo.updateColor(color, noteid, id );
	}
	
	
	
	

}
