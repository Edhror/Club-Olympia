package it.capgemini.clubOlympia.entities.dto;

public class ChangeEnrollmentDTO {
	private int clientId;
	private int trainingCampId;
	private String status;
	
	public ChangeEnrollmentDTO(int clientId, int trainingCampId, String status) {
		this.clientId = clientId;
		this.trainingCampId = trainingCampId;
		this.status = status;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTrainingCampId() {
		return trainingCampId;
	}

	public void setTrainingCampId(int trainingCampId) {
		this.trainingCampId = trainingCampId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
