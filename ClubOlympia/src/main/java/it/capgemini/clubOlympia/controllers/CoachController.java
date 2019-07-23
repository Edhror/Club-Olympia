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

import it.capgemini.clubOlympia.abstraction.service.CoachService;
import it.capgemini.clubOlympia.entities.Coach;
import it.capgemini.clubOlympia.entities.dto.CoachForSelectionDTO;

@RequestMapping("/api")
@CrossOrigin
@RestController
public class CoachController {

	@Autowired
	CoachService coachService;
	
	@GetMapping("/coaches")
	public ResponseEntity<Iterable<CoachForSelectionDTO>> allCoaches(){
		Iterable<Coach> coaches = coachService.all();
		Stream<Coach> coachStream = StreamSupport.stream(coaches.spliterator(), false);
		List<CoachForSelectionDTO> dtos = coachStream
				.map(CoachForSelectionDTO::toCoachDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
}
