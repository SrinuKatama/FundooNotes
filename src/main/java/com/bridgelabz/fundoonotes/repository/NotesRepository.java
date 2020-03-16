package com.bridgelabz.fundoonotes.repository;

import java.time.LocalDateTime;
import java.util.List;

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
	  @Query(value ="insert into NotesTable(Note_Title,Content,CreatedTime,noteId) values(?,?,?,?)" ,nativeQuery=true) 
	  void addNotes(String noteTitle ,String  content,LocalDateTime createdtime, Long noteid);
	  
	  @Query(value = "select * from NotesTable where noteId=(select Max(note_id) from notes)", nativeQuery = true)
		Notes selectLastNotes();
	  
	  @Query(value="select * from notes where noteId =?",nativeQuery = true)
	  Notes findByNoteId(Long noteid);
	  
	  @Modifying
	  @Query(value="update NotesTable set Color=? where noteId=? and ID=?")
	  void updateColor(String color,Long noteid,Long id);
	  
	  @Modifying
	  @Query(value="update NotesTable set Pinned_or_Not=? where noteId=? and ID=?" )
	  boolean updatePin(boolean isPin,Long noteid,Long id);
	 
	  @Modifying
	  @Query(value="update NotesTable set Archive_or_Not=? where noteId=? and ID=?",nativeQuery = true)
	  boolean updateArchive( boolean isArchive,Long noteid,Long id);
	  
	  @Modifying
	  @Query(value = "update NotesTable set Note_Title=?,Content=? where noteId=? AND ID=?",nativeQuery = true)
	  void updateNotes(String noteTitle,String content, Long noteid,Long id);
	  
	  @Modifying
	  @Query(value = "update NotesTable set Deleted_or_Not=?   where noteId=? and ID=?", nativeQuery = true)
	   void updateTrash(boolean isTrash, Long noteid,Long id);
	 
	  @Modifying
	  @Query(value="delete from NotesTable where noteId=? and ID=? ",nativeQuery = true)
	  void deleteNotes(Long noteid,Long id);
	  
	  @Modifying
	  @Query(value="update NotesTable set PickDate_and_Time=?,Pick_Location=?  where noteId=? and ID=? ")
	  void remaind(LocalDateTime remaindAt,String remaindMe,Long noteid,Long id);
	
	  @Query(value="select * from NotesTable where  noteId=? and ID=?")
	  List<Notes> getListOfNotes(Long noteid,Long id);
	  
	  @Query(value="select * from NotesTable where  noteId=?")
	  List<Notes> getListOfNotesbyid(Long Id);
	  
	  @Query(value = "select * from NotesTable where  noteId= ? ORDER BY Note_Title", nativeQuery = true)
	  List<Notes> getAllNotesByName(Long id);
	  
	  @Query(value = "select * from NotesTable where  noteId= ?",nativeQuery = true)
	  List<Notes> getAllNotesByLocaldateandtime(Long id);
	  
	  @Query(value="select * from NotesTable where Archive_or_Not=? ")
	  List<Notes> getAllNotesByArchive(Long id);

}
