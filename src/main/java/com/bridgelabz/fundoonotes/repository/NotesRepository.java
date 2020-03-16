package com.bridgelabz.fundoonotes.repository;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Notes;

@Repository
@Transactional
public interface NotesRepository  extends JpaRepository<Notes, Long>
{
	
	  @Modifying
	  @Query(value ="insert into notes(Note_Title,Content,CreatedTime,noteId) values(?,?,?,?)" ,nativeQuery=true) 
	  void addNotes(String noteTitle ,String  content,LocalDateTime createdtime, Long noteid);
	  
	  @Query(value = "select * from notes where noteId=(select Max(note_id) from notes)", nativeQuery = true)
		Notes selectLastNotes();
	  
	  @Query(value="select * from notes where noteId =?",nativeQuery = true)
	  Notes findByNoteId(Long noteid);
	  
	 
	 
	 
	
		
	

}
