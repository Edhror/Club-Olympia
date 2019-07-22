package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Court;

public class CourtForSelectionDTO {

	private int id;
	private String courtName;

	public CourtForSelectionDTO(int id, String courtName) {
		this.id = id;
		this.courtName = courtName;
	}
	
	public CourtForSelectionDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}


	public static CourtForSelectionDTO toCourtDto(Court court) {
		return new CourtForSelectionDTO(court.getId(), court.getName());
	}
	
}
