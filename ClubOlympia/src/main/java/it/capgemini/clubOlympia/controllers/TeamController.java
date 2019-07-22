package it.capgemini.clubOlympia.controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import it.capgemini.clubOlympia.abstraction.service.ClientService;
import it.capgemini.clubOlympia.abstraction.service.TeamService;
import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.Team;
import it.capgemini.clubOlympia.entities.dto.TeamDTO;
import it.capgemini.clubOlympia.entities.dto.SquadraInputDTO;

@RestController
@RequestMapping("/api")
public class TeamController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeamService teamService;
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/team")
	public Iterable<TeamDTO> all() {
		logger.info("calling all reservation method");
		Iterable<Team> all = teamService.list();
		Stream<Team> streamRes = StreamSupport.stream(all.spliterator(), false);
		return streamRes.map(r -> TeamDTO.teamToDTO(r)).collect(Collectors.toList());
	}
	
	@GetMapping("/team/{id}")
	public TeamDTO find(@PathVariable int id) throws SQLException {
		logger.info("calling find team method");
		Team found = teamService.byId(id);
		if (found == null) {
			throw new SQLException("team not found");
		}
		TeamDTO dto = TeamDTO.teamToDTO(found);
		return dto;
	}
	
	@PostMapping("/team")
	public ResponseEntity<SquadraInputDTO> add(
			@RequestBody SquadraInputDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		logger.info("calling add team method");
		Team squad = dto.toSquadra();
		
		List<Client> clients = dto.getPlayersId().stream().map( i -> clientService.findById(i)).collect(Collectors.toList());
		squad.setTeam(clients);
		
		teamService.save(squad);
		SquadraInputDTO result = SquadraInputDTO.squadraToDTO(squad);
		UriComponents uriComponents = 
		        uriComponentsBuilder.path("/team/{id}").buildAndExpand(result.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(result);
	}
	
	
	@PutMapping("/team/{id}")
	public ResponseEntity<SquadraInputDTO> update(@PathVariable int id, @RequestBody SquadraInputDTO dto)
			throws SQLException {
		logger.info("calling update reservation method");
		if(dto.getId() !=  id) {
			throw new SQLException("bad input data: id parameter must be equal to reservation id");
		}
		Team squad = dto.toSquadra();
		List<Client> clients = dto.getPlayersId().stream().map( i -> clientService.findById(i)).collect(Collectors.toList());
		if(clients == null || clients.size() != dto.getPlayersId().size()) {
			throw new SQLException("team not found");
		}
		squad.setTeam(clients);
		
		teamService.save(squad);
		SquadraInputDTO result = SquadraInputDTO.squadraToDTO(squad);
		return new ResponseEntity<SquadraInputDTO>(result, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/team/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws SQLException {
		logger.info("calling delete reservation method");
		Team found = teamService.byId(id);
		if(found== null) {
			throw new SQLException("team not found");
		}
		teamService.delete(id);
		return ResponseEntity.noContent().build();
	}
}








