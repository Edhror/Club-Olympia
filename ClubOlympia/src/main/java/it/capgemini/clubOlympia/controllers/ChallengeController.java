package it.capgemini.clubOlympia.controllers;


import java.sql.SQLException;
import java.time.LocalDateTime;
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

import it.capgemini.clubOlympia.abstraction.service.ChallengeService;
import it.capgemini.clubOlympia.abstraction.service.TeamService;
import it.capgemini.clubOlympia.entities.Challenge;
import it.capgemini.clubOlympia.entities.Reservation;
import it.capgemini.clubOlympia.entities.Team;
import it.capgemini.clubOlympia.entities.dto.ChallengeDTO;
import it.capgemini.clubOlympia.entities.dto.ChallengeInputDTO;

@RestController
@RequestMapping("/api")
public class ChallengeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	private TeamService squadraService;	
	
	@GetMapping("/challenge")
	public Iterable<ChallengeDTO> all() {
		logger.info("calling all reservation method");
		Iterable<Challenge> all = challengeService.list();
		Stream<Challenge> streamRes = StreamSupport.stream(all.spliterator(), false);
		return streamRes.map(r -> ChallengeDTO.challengeToDTO(r)).collect(Collectors.toList());
	}
	
	@GetMapping("/challenge/{id}")
	public ChallengeDTO find(@PathVariable int id) throws SQLException {
		logger.info("calling find challenge method");
		Challenge found = challengeService.byId(id);
		if (found == null) {
			throw new SQLException("challenge not found");
		}
		ChallengeDTO dto = ChallengeDTO.challengeToDTO(found);
		return dto;
	}
	
	@GetMapping("/challenge/{date}")
	public ChallengeDTO find(@PathVariable LocalDateTime date) throws SQLException {
		logger.info("calling find challenge method");
		List<Challenge> found = challengeService.byDate(date);
		if (found == null) {
			throw new SQLException("no challenges found");
		}
		ChallengeDTO dto = null;
		for(Challenge c : found)
			dto = ChallengeDTO.challengeToDTO(c);
		return dto;
	}
	
	@PostMapping("/challenge")
	public ResponseEntity<ChallengeInputDTO> add(
			@RequestBody ChallengeInputDTO dto, UriComponentsBuilder uriComponentsBuilder) {
		logger.info("calling add challenge method");
		Challenge cha = dto.toChallenge();
		
		Reservation reservation = challengeService.byId(dto.getIdreservation()).getReservation();
		if(reservation == null) {
			return new ResponseEntity<ChallengeInputDTO>(HttpStatus.NOT_FOUND);
		}
		
		Team squadra1 = squadraService.byId(dto.getIdSquadra1());
		if(squadra1 == null) {
			return new ResponseEntity<ChallengeInputDTO>(HttpStatus.NOT_FOUND);
		}
		
		Team squadra2 = squadraService.byId(dto.getIdSquadra2());
		if(squadra2 == null) {
			return new ResponseEntity<ChallengeInputDTO>(HttpStatus.NOT_FOUND);
		}
		
		cha.setReservation(reservation);
		cha.setSquadra1(squadra1);
		cha.setSquadra2(squadra2);
		
		challengeService.save(cha);
		ChallengeInputDTO result = ChallengeInputDTO.challengeToDTO(cha);
		UriComponents uriComponents = 
		        uriComponentsBuilder.path("/challenge/{id}").buildAndExpand(result.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(result);
	}
	
	
	@PutMapping("/challenge/{id}")
	public ResponseEntity<ChallengeInputDTO> update(@PathVariable int id, @RequestBody ChallengeInputDTO dto)
			throws SQLException {
		logger.info("calling update reservation method");
		if(dto.getId() !=  id) {
			throw new SQLException("bad input data: id parameter must be equal to reservation id");
		}
		Challenge cha = dto.toChallenge();
		Reservation reservation = challengeService.byId(dto.getIdreservation()).getReservation();
		if(reservation == null) {
			throw new SQLException("challenge not found");
		}
		Team squadra1 = squadraService.byId(dto.getIdSquadra1());
		if(squadra1 == null) {
			throw new SQLException("squadra1 not found");
		}
		Team squadra2 = squadraService.byId(dto.getIdSquadra2());
		if(squadra2 == null) {
			throw new SQLException("squadra2 not found");
		}
		cha.setReservation(reservation);
		cha.setSquadra1(squadra1);
		cha.setSquadra2(squadra2);
		
		challengeService.update(cha);
		ChallengeInputDTO result = ChallengeInputDTO.challengeToDTO(cha);
		return new ResponseEntity<ChallengeInputDTO>(result, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/challenge/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws SQLException {
		logger.info("calling delete reservation method");
		Challenge found = challengeService.byId(id);
		if(found== null) {
			throw new SQLException("challenge not found");
		}
		challengeService.delete(id);
		return ResponseEntity.noContent().build();
	}
}








