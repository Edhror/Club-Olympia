package it.capgemini.clubOlympia.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Coach extends Client {
	
	private String bio;

	public Coach() {
		super();
	}

	public Coach(int id, String name, String lastname, LocalDate dateofbirth, Sex sex, String bio) {
		super(id, name, lastname, dateofbirth, sex);
		this.bio = bio;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	
}
