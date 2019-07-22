package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Coach;

public class CoachForSelectionDTO {

	private int id;
	private String coachName;

	public CoachForSelectionDTO(int id, String coachName) {
		this.id = id;
		this.coachName = coachName;
	}

	public CoachForSelectionDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public static CoachForSelectionDTO toCoachDto(Coach coach) {
		return new CoachForSelectionDTO(coach.getId(), coach.getName() + " " + coach.getLastname());
	}

}
