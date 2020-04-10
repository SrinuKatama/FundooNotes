package com.bridgelabz.fundoonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profilepicture")
public class ProfilePic
{

	@Column(name="Pid")
	private Long profileid;
	
	@Column(name="Profilename")
	private String profilename;

	@ManyToOne
	@JoinColumn(name="ID")
	private UserDetails userdetails;

	public Long getProfileid() {
		return profileid;
	}

	public void setProfileid(Long profileid) {
		this.profileid = profileid;
	}

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

	public UserDetails getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}
	
	
	

}
