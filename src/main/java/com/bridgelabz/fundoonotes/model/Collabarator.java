
  package com.bridgelabz.fundoonotes.model;
  /* Author:Srinu*/
  
  import java.time.LocalDateTime;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.JoinColumn; import
  javax.persistence.ManyToOne; import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnore;
  
  import lombok.AllArgsConstructor; import lombok.Getter; import
  lombok.NoArgsConstructor; import lombok.Setter;
  
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Entity
  @Table(name="ColabaratorTable")
  public class Collabarator {
  @Id
  @Column(name="collabarator_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cid;
  
  @Column(name="collabarator_mail")
  private String collabaratormail;
  
  @Column(name="colabaration_time")
  private LocalDateTime collabarationtime;
  
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="noteId")
  private Notes Notes;
  
  public Long getCid() {
	return cid;
}

public void setCid(Long cid) {
	this.cid = cid;
}

public String getCollabaratormail() {
	return collabaratormail;
}

public void setCollabaratormail(String collabaratormail) {
	this.collabaratormail = collabaratormail;
}

public LocalDateTime getCollabarationtime() {
	return collabarationtime;
}

public void setCollabarationtime(LocalDateTime collabarationtime) {
	this.collabarationtime = collabarationtime;
}

public Notes getNotes() {
	return Notes;
}

public void setNotes(Notes notes) {
	Notes = notes;
}


  
  
  
  }
 