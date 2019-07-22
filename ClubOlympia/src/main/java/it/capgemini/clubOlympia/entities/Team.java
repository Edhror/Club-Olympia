package it.capgemini.clubOlympia.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Team {

	@Id
	@SequenceGenerator(name="teamSeq",sequenceName="public.team_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="teamSeq")
	private int id;
	
	@Column
	private final int maxPlayers;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="team_id")
	private List<Client> team;

	public Team(int id,int maxPlayers) {
		this.id = id;
		this.maxPlayers = maxPlayers;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public List<Client> getTeam() {
		return team;
	}

	public void setTeam(List<Client> team) {
		this.team = team;
	}

	public void aggiungiGiocatore(Client client) {
		team.add(client);
	}
	
	public void togliGiocatore(Client client) {
		team = team.stream().filter( p -> p.getId() != client.getId()).collect(Collectors.toList());
	}
}
