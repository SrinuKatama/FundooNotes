package com.bridgelabz.fundoonotes.model;

/*   Author : SrinuKatama

*/
import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Table(name = "user" )
public class UserDetails 
{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="First_name")
	@NotEmpty(message="please fill with out blank")
	private String firstname;
	
	@Column(name="Last_name")
	@NotEmpty(message="please fill with out blank")
	private String lastname;
	
	@Column(name="User_email")
	@NotEmpty(message="please fill with out blank")
	private String useremail;
	
	@Column(name="Password")
	@NotEmpty(message="please fill with out blank")
	private String password;
	
	@Column(name="Mobilenumber")
//	@Pattern(regexp="\\\\d{3}[-\\\\.\\\\s]\\\\d{3}[-\\\\.\\\\s]\\\\d{4}")   //123-456-7890
	@NotEmpty(message="please fill with out blank")
	private String mobileno;
	
	@Column(name="LocalDateTime")
	private LocalDateTime localdatetime;
	
	@Column(name="Isverified")
	private boolean isVerified;
	
	
 	private List<Notes> notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public LocalDateTime getLocaldatetime() {
		return localdatetime;
	}

	public void setLocaldatetime(LocalDateTime localdatetime) {
		this.localdatetime = localdatetime;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	

}
