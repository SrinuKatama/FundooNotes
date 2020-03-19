package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Lable;


@Repository
public interface LableRepository extends JpaRepository<Lable,  Long>  
{
	@Query(value = "select * from Lable_Table where lableid=? AND ID=?",nativeQuery = true)
	Lable findBylableid(Long lableid,Long id);
	
	@Query(value = "select * from Lable_Table where Lable_Name=? AND ID=?",nativeQuery = true)
	Lable findbylablename(String lableName,Long id);
	
	@Modifying
	@Query(value = "insert into Lable_Table(lableName,ID) values(??)",nativeQuery = true)
	Lable insertlable(String lableName,Long id);
	
	@Query(value = "select * from Lable_Table where Lable_id=? ")
	Lable selectbyid(Long lableid);
	
	@Modifying
	@Query(value = "update Lable_Table set Lable_Name=? where lableid=? AND ID=?")
	void updatelable(String lablename,Long lableid,Long id);
	
	@Modifying
	@Query(value = "delete from Lable_Table where Lable_id=? ANd ID=?")
	void deleteData(Long lableid,Long id);
	
	@Modifying
	@Query(value = "select * from Lable_Table_NotesTable where Lable_id=? ANd noteId=?",nativeQuery = true)
	Object findbylableidandnoteid(Long lableid,Long noteid);
	
	@Modifying
	@Query(value = "insert into Lable_Table_NotesTable values(?,?)",nativeQuery = true)
	void insertmappingdata(Long lableid,Long noteid);
	
	@Query(value = "select * from Lable_Table where ID=?")
	List<Lable> getAllLables(Long id);
	
	@Query(value = "select * from Lable_Table where Lable_id=(select MAX(Lable_id) from noteId")
	Lable selectlastlable();
	
	
	
	

}
