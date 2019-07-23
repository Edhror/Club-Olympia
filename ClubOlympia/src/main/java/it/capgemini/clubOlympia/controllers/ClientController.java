package it.capgemini.clubOlympia.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.capgemini.clubOlympia.abstraction.service.ClientService;
import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.dto.ClientForSelectionDTO;

@RequestMapping("/api")
@CrossOrigin
@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<Iterable<ClientForSelectionDTO>> all(){
		Iterable<Client> clients = clientService.all();
		Stream<Client> streamClients = StreamSupport.stream(clients.spliterator(), false);
		List<ClientForSelectionDTO> dtos = streamClients
				.map(ClientForSelectionDTO::toClientDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
}
