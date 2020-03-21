package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Collabarator;


@Repository
public interface Collabaratorrepository extends JpaRepository<Collabarator, Long> 
{
	
	@Query(value="select * from ColabaratorTable where collabarator_mail=? AND noteId=?",nativeQuery=true)
	Collabarator findbymail(String collabaratormail,Long noteid);
	
	
	@Query(value ="select * from ColabaratorTable where collabarator_mail=?",nativeQuery = true)
	Collabarator findbycollmail(String collabaratormail);
	
	@Query(value = "insert into ColabaratorTable values(?,?)",nativeQuery = true)
	void insertData(String collabaratormail,Long noteid);
	
	@Modifying
	@Query(value = "delete from ColabaratorTable where collabarator_id=?")
	void deleteData(Long cid);
	
	@Query(value = "select * from ColabaratorTable ")
	List<Collabarator> getusers(Long noteid);
	

	
	
	
}