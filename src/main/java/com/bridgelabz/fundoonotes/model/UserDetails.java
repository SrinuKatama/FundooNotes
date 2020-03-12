package com.bridgelabz.fundoonotes.model;

/*   Author : SrinuKatama
*/
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user" ,uniqueConstraints = @UniqueConstraint(columnNames = {"User_email,Mobilenumber"}))

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
	@Pattern(regexp="\\\\d{3}[-\\\\.\\\\s]\\\\d{3}[-\\\\.\\\\s]\\\\d{4}")   //123-456-7890
	@NotEmpty(message="please fill with out blank")
	private String mobileno;
	
	@Column(name="LocalDateTime")
	private LocalDateTime localdatetime;
	
	@Column(name="Isverified")
	private boolean isVerified;
	

}
