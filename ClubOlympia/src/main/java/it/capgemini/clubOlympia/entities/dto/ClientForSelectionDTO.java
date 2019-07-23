package it.capgemini.clubOlympia.entities.dto;

import it.capgemini.clubOlympia.entities.Client;

public class ClientForSelectionDTO {
	
	
	private int id;
	private String name;

	public ClientForSelectionDTO(int id, String clientName) {
		this.id = id;
		this.name = clientName;
	}
	
	public ClientForSelectionDTO() {
		
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

	public void setName(String clientName) {
		this.name = clientName;
	}


	public static ClientForSelectionDTO toClientDto(Client client) {
		return new ClientForSelectionDTO(client.getId(), client.getName() + " " + client.getLastname());
	}
	
}
