package it.capgemini.clubOlympia.entities.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.capgemini.clubOlympia.entities.Team;

public class TeamDTO {
	private int id;
	private int maxPlayer;
	private List<String> playersId;
	
    
	public TeamDTO(int id, int maxPlayer, List<String> playersId) {
		this.id = id;
		this.maxPlayer = maxPlayer;
		this.playersId = playersId;
	}
	
	public TeamDTO() {}
	
	public Team toSquadra() {
		return new Team(this.id, this.maxPlayer);
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public List<String> getPlayersId() {
		return playersId;
	}

	public void setPlayersId(ArrayList<String> playersId) {
		this.playersId = playersId;
	}

	public static TeamDTO teamToDTO(Team squad) {
		return new TeamDTO(squad.getId(), squad.getMaxPlayers(), squad.getTeam().stream().map( p -> p.getName() + p.getLastname() ).collect(Collectors.toList()));
	}
	
}
