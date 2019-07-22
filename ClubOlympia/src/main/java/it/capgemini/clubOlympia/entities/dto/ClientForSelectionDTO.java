package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Client;

public class ClientForSelectionDTO {
	
	
	private int id;
	private String clientName;

	public ClientForSelectionDTO(int id, String clientName) {
		this.id = id;
		this.clientName = clientName;
	}
	
	public ClientForSelectionDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public static ClientForSelectionDTO toClientDto(Client client) {
		return new ClientForSelectionDTO(client.getId(), client.getName() + " " + client.getLastname());
	}
	
}
