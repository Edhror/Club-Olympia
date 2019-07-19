package it.capgemini.clubOlympia.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {
	@Id
	@SequenceGenerator(name="clientSeq",sequenceName="public.client_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="clientSeq")
	private int id;
	@Column(name= "firstname")
	private String name;
	private String lastname;
	@Column(name= "date_of_birth")
	private LocalDate dateOfBirth;
	@Enumerated(EnumType.STRING)
	private Sex sex;

	public Client(int id, String name, String lastname, LocalDate dateofbirth, Sex sex) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.dateOfBirth = dateofbirth;
		this.sex = sex;
	}

	public Client() {
	}

	
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDateofbirth() {
		return dateOfBirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateOfBirth = dateofbirth;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}
    
}
