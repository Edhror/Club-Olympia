package it.capgemini.clubOlympia.entities.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.capgemini.clubOlympia.entities.Team;

public class SquadraInputDTO {
	private int id;
	private int maxPlayer;
	private List<Integer> playersId;

	public SquadraInputDTO(int id, int maxPlayer, List<Integer> playersId) {
		this.id = id;
		this.maxPlayer = maxPlayer;
		this.playersId = playersId;
	}

	public SquadraInputDTO() {}
	
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

	public List<Integer> getPlayersId() {
		return playersId;
	}

	public void setPlayersId(List<Integer> playersId) {
		this.playersId = playersId;
	}

	public Team toSquadra() {
		return new Team(this.id, this.maxPlayer);
	}

	public static SquadraInputDTO squadraToDTO(Team squad) {
		return new SquadraInputDTO(squad.getId(), squad.getMaxPlayers(), squad.getTeam().stream().map( p -> Integer.reverse( p.getId() ) ).collect(Collectors.toList()));
	}

}
