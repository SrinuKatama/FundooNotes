/*
 * package com.bridgelabz.fundoonotes.model;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToMany; import
 * javax.persistence.ManyToOne; import javax.persistence.Table;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity
 * 
 * @Table(name = "Lable")
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor public class Lable {
 * 
 * @Column(name = "Lable_id") private long id;
 * 
 * @Column(name = "Lable_Name")
 * 
 * @JsonIgnore private String lableName;
 * 
 * @JoinColumn(name = "ID")
 * 
 * @JsonIgnore
 * 
 * @ManyToOne private UserDetails UserDetails;
 * 
 * @ManyToMany
 * 
 * @JsonIgnore private List<Notes> notes;
 * 
 * }
 */