package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="colabarator")

public class Collabarator 
{
	@Id
	@Column(name="collabarator_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	
	@Column(name="collabarator_mail")
	private String email;
	
	/*
	 * @Column(name="colabaration_time") private LocalDateTime collabarationtime;
	 */
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="noteId")
	private Notes Notes;
	
	

}
