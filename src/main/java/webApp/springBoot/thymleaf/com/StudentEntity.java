package webApp.springBoot.thymleaf.com;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class StudentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String hobby;
	private String knownlanguage;
	private String location;
	private String designation;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getKnownlanguage() {
		return knownlanguage;
	}
	public void setKnownlanguage(String knownlanguage) {
		this.knownlanguage = knownlanguage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

}
