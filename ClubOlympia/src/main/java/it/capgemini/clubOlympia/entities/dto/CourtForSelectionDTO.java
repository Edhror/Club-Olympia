package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Court;

public class CourtForSelectionDTO {

	private int id;
	private String name;

	public CourtForSelectionDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CourtForSelectionDTO() {
		
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

	public void setName(String courtName) {
		this.name = courtName;
	}


	public static CourtForSelectionDTO toCourtDto(Court court) {
		return new CourtForSelectionDTO(court.getId(), court.getName());
	}
	
}
