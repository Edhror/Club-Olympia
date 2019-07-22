package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Challenge;

public class ChallengeInputDTO {
	private int id;
	private int idreservation;
	private int idSquadra1;
    private int idSquadra2;

	public ChallengeInputDTO(int id, int idreservation, int idSquadra1, int idSquadra2) {
		this.id = id;
		this.idreservation = idreservation;
		this.idSquadra1 = idSquadra1;
		this.idSquadra2 = idSquadra2;
	}

	public ChallengeInputDTO() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}

	public int getIdSquadra1() {
		return idSquadra1;
	}

	public void setIdSquadra1(int idSquadra1) {
		this.idSquadra1 = idSquadra1;
	}

	public int getIdSquadra2() {
		return idSquadra2;
	}

	public void setIdSquadra2(int idSquadra2) {
		this.idSquadra2 = idSquadra2;
	}

	public Challenge toChallenge() {
		return new Challenge(this.id, null,null,null);
	}

	public static ChallengeInputDTO challengeToDTO(Challenge cha) {
		return new ChallengeInputDTO(cha.getId(), 
				cha.getReservation().getId(), 
				cha.getSquadra1().getId(),
				cha.getSquadra2().getId()
				);
	}

}
