/*
 * package com.bridgelabz.fundoonotes.model;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToMany; import javax.persistence.ManyToOne; import
 * javax.persistence.Table;
 * 
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @Table(name="Notes")
 * 
 * public class Notes {
 * 
 * @Id
 * 
 * @Column(name="noteId")
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long noteid;
 * 
 * @Column(name="Note_Title") private String noteTitle;
 * 
 * @Column(name="Content") private String content;
 * 
 * @Column(name="Pinned_or_Not" ,columnDefinition = "boolean default false")
 * private boolean isPin;
 * 
 * @Column(name="Archive_or_Not",columnDefinition = "boolean default false")
 * private boolean isArchive;
 * 
 * @Column(name="CreatedTime") private LocalDateTime createdtime;
 * 
 * @Column(name="PickDate_and_Time") private LocalDateTime remaindAt;
 * 
 * @Column(name="Pick_Location",columnDefinition =
 * "varchar(100) default 'does not repeat'") private String remaindMe;
 * 
 * @Column(name="Deleted_or_Not" ,columnDefinition = "boolean default false")
 * private boolean isTrash;
 * 
 * @Column(name="Color" , columnDefinition = "varcahr(100) default '#ffffff'")
 * private String color;
 * 
 * @JsonIgnore
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="ID") private UserDetails user;
 * 
 * @ManyToMany
 * 
 * @JsonIgnore private List<Lable> lables;
 * 
 * }
 */