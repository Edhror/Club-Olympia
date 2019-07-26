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

import it.capgemini.clubOlympia.entities.Court;
import it.capgemini.clubOlympia.entities.dto.CourtForSelectionDTO;
import it.capgemini.clubOlympia.implementations.service.CourtServiceStandard;

@RequestMapping("/api")
@CrossOrigin
@RestController
public class CourtController {

	@Autowired
	CourtServiceStandard courtService;
	
	@GetMapping("/courts")
	public ResponseEntity<Iterable<CourtForSelectionDTO>> allCourts(){
		Iterable<Court> courts = courtService.all();
		Stream<Court> courtStream = StreamSupport.stream(courts.spliterator(), false);
		List<CourtForSelectionDTO> dtos = courtStream
				.map(CourtForSelectionDTO::toCourtDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
}
