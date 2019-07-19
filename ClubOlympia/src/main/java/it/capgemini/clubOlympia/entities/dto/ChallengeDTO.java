package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Challenge;
import it.capgemini.clubOlympia.entities.Reservation;
import it.capgemini.clubOlympia.entities.Team;

public class ChallengeDTO {
	private int id;
	private Reservation reservation;
	private Team Squadra1;
    private Team Squadra2;
	
    
	public ChallengeDTO(int id, Reservation reservation, Team squadra1, Team squadra2) {
		this.id = id;
		this.reservation = reservation;
		this.Squadra1 = squadra1;
		this.Squadra2 = squadra2;
	}
	
	public ChallengeDTO() {}
	
	public Challenge toChallenge() {
		return new Challenge(
				this.id,
				null,
				null,
				null	
				);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Team getSquadra1() {
		return Squadra1;
	}

	public void setSquadra1(Team squadra1) {
		Squadra1 = squadra1;
	}

	public Team getSquadra2() {
		return Squadra2;
	}

	public void setSquadra2(Team squadra2) {
		Squadra2 = squadra2;
	}

	public static ChallengeDTO challengeToDTO(Challenge cha) {
		return new ChallengeDTO(
				cha.getId(),
			    cha.getReservation(),
			    cha.getSquadra1(),
			    cha.getSquadra2()
				);
	}
    
}
