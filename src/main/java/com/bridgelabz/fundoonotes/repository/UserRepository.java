package com.bridgelabz.fundoonotes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.model.UserDetails;

@Repository
@Transactional
public interface UserRepository extends JpaRepositoryImplementation<UserDetails, Long>
{
	@Query(value="select * from user where User_email=? ",nativeQuery = true)
	UserDetails findByEmail(String useremail);
	
	@Query(value="select * from user where ID=? ",nativeQuery = true)
	UserDetails findById(String id);
	
	@Query(value="insert into user(First_name,Last_name,User_email,Password,Mobilenumber,LocalDateTime) values(?,?,?,?,?,?)" ,nativeQuery = true)
	void saveData(String firstname,String lastname,String useremail,String password,String mobileno,LocalDateTime localdatetime);
	
	@Modifying
	@Query(value="Update user set Isverified =true where User_email=? ",nativeQuery = true)
	void updateIsVeified(String useremail);
	
	@Modifying
	@Query(value="Update user set Password=? where User_email=? ",nativeQuery = true)
	void updatePassword(String password,String useremail);
	
	@Query(value="select * from user",nativeQuery = true)
	List<UserDetails> getUserList();
	

}
