package it.capgemini.clubOlympia.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {
	@Id
	@SequenceGenerator(name="clientSeq",sequenceName="public.client_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="clientSeq")
	protected int id;
	@Column(name= "firstname")
	protected String name;
	protected String lastname;
	@Column(name= "date_of_birth")
	protected LocalDate dateOfBirth;
	@Enumerated(EnumType.STRING)
	protected Sex sex;
	
	@ManyToMany(mappedBy = "clients")
	protected List<TrainingCamp> trainingcamps = new ArrayList<>();

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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateofbirth) {
		this.dateOfBirth = dateofbirth;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}

	public List<TrainingCamp> getTrainingCamps() {
		return trainingcamps;
	}

	public void setTrainingcamps(List<TrainingCamp> trainingcamps) {
		this.trainingcamps = trainingcamps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
