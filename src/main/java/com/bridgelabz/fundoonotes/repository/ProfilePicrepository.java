package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.model.ProfilePic;

@Repository
@Transactional
public interface ProfilePicrepository extends JpaRepository<ProfilePic, Long> {
	@Modifying
	@Query(value = "insert into profilepicture(Profilename,Pid)values(?,?)", nativeQuery = true)
	void saveData(String filename, Long id);

	@Query(value = "select * from profilepicture where Pid=?", nativeQuery = true)
	ProfilePic searchById(Long profileid);

	@Modifying
	@Query(value = "delete from profilepicture where  Pid=?", nativeQuery = true)
	void deleteData(Long profileId);

	@Query(value = "select max(id) from profilepicture where user_id=:ID", nativeQuery = true)
	Long getPicByUser(Long id);

}
