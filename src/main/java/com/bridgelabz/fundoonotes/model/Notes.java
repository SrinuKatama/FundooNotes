
package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NotesTable")
public class Notes 
{

	@Id
	@Column(name = "noteId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noteid;

	@Column(name = "Note_Title")
	private String noteTitle;

	@Column(name = "Content")
	private String content;

	@Column(name = "Pinned_or_Not", columnDefinition = "boolean default false")
	private boolean isPin;

	@Column(name = "Archive_or_Not", columnDefinition = "boolean default false")
	private boolean isArchive;

	

	@Column(name = "PickDate_and_Time")
	private LocalDateTime remaindAt;

	@Column(name = "Pick_Location", columnDefinition = "varchar(100) default 'does not repeat'")
	private String remaindMe;

	@Column(name = "Deleted_or_Not", columnDefinition = "boolean default false")
	private boolean isTrash;

	@Column(name = "Color", columnDefinition = "varcahr(100) default '#ffffff'")
	private String color;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID")
	private UserDetails user;

	
	/*
	 * @ManyToMany
	 * 
	 * @JsonIgnore private List<Lable> lables;
	 */


	public long getNoteid() {
		return noteid;
	}


	public void setNoteid(long noteid) {
		this.noteid = noteid;
	}


	public String getNoteTitle() {
		return noteTitle;
	}


	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public boolean isPin() {
		return isPin;
	}


	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}


	public boolean isArchive() {
		return isArchive;
	}


	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}



	public LocalDateTime getRemaindAt() {
		return remaindAt;
	}


	public void setRemaindAt(LocalDateTime remaindAt) {
		this.remaindAt = remaindAt;
	}


	public String getRemaindMe() {
		return remaindMe;
	}


	public void setRemaindMe(String remaindMe) {
		this.remaindMe = remaindMe;
	}


	public boolean isTrash() {
		return isTrash;
	}


	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public UserDetails getUser() {
		return user;
	}


	public void setUser(UserDetails user) {
		this.user = user;
	}

	/*
	 * public List<Lable> getLables() { return lables; }
	 */


	/*
	 * public void setLables(List<Lable> lables) { this.lables = lables; }
	 */
	 

}
