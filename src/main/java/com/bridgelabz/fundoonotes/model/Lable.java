
  package com.bridgelabz.fundoonotes.model;
  
  import java.util.List;
  
  import javax.persistence.Column; import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import
  javax.persistence.JoinColumn; import javax.persistence.ManyToMany; import
  javax.persistence.ManyToOne; import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnore;
  
  import lombok.AllArgsConstructor; import
  lombok.NoArgsConstructor;
  
  @Entity
  @Table(name = "Lable_Table")
  public class Lable
  {
  @Column(name = "Lable_id") 
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long lableid;
  
  @Column(name = "Lable_Name")
  @JsonIgnore 
  private String lableName;
  
  @JoinColumn(name = "ID")
  @JsonIgnore
  @ManyToOne 
  private UserDetails lableuser;
  
  @ManyToMany(mappedBy = "lables")
  @JsonIgnore 
  private List<Notes> notes;

public long getLableid() {
	return lableid;
}

public void setLableid(long lableid) {
	this.lableid = lableid;
}

public String getLableName() {
	return lableName;
}

public void setLableName(String lableName) {
	this.lableName = lableName;
}

public UserDetails getLableuser() {
	return lableuser;
}

public void setLableuser(UserDetails lableuser) {
	this.lableuser = lableuser;
}

public List<Notes> getNotes() {
	return notes;
}

public void setNotes(List<Notes> notes) {
	this.notes = notes;
}
  
  
  }
 