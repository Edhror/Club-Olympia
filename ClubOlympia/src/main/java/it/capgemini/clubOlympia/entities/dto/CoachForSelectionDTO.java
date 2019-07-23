package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Coach;

public class CoachForSelectionDTO {

	private int id;
	private String name;

	public CoachForSelectionDTO(int id, String coachName) {
		this.id = id;
		this.name = coachName;
	}

	public CoachForSelectionDTO() {

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

	public void setName(String coachName) {
		this.name = coachName;
	}

	public static CoachForSelectionDTO toCoachDto(Coach coach) {
		return new CoachForSelectionDTO(coach.getId(), coach.getName() + " " + coach.getLastname());
	}

}
