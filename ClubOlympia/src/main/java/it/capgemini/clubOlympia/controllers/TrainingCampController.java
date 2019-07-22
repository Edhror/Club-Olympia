package it.capgemini.clubOlympia.controllers;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import it.capgemini.clubOlympia.abstraction.service.CoachService;
import it.capgemini.clubOlympia.abstraction.service.CourtService;
import it.capgemini.clubOlympia.abstraction.service.TrainingCampService;
import it.capgemini.clubOlympia.entities.Coach;
import it.capgemini.clubOlympia.entities.Court;
import it.capgemini.clubOlympia.entities.TrainingCamp;
import it.capgemini.clubOlympia.entities.dto.TrainingCampDTO;
import it.capgemini.clubOlympia.exception.BadRequestException;
import it.capgemini.clubOlympia.exception.ResourceNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TrainingCampController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrainingCampService trainingCourtService;
	@Autowired
	private CourtService courtService;
	@Autowired
	private CoachService coachService;
	
	@GetMapping("/training-camp")
	public Iterable<TrainingCampDTO> all() {
		logger.info("calling all reservation method");
		Iterable<TrainingCamp> all = trainingCourtService.allTrainingCamp();
		Stream<TrainingCamp> streamRes = StreamSupport.stream(all.spliterator(), false);
		return streamRes.map(r -> TrainingCampDTO.toTrainingCampDto(r)).collect(Collectors.toList());
	}

	@GetMapping("/training-camp/{id}")
	public TrainingCampDTO find(@PathVariable int id) {
		logger.info("calling find reservation method");
		TrainingCamp found = trainingCourtService.findById(id);
		if (found == null) {
			throw new ResourceNotFoundException("Training Camp not found");
		}
		TrainingCampDTO dto = TrainingCampDTO.toTrainingCampDto(found);
		return dto;
	}

	@PostMapping("/training-camp")
	public ResponseEntity<TrainingCampDTO> add(@RequestBody TrainingCampDTO dto,
			UriComponentsBuilder uriComponentsBuilder) {
		logger.info("calling add training camp method");
		TrainingCamp tcamp = dto.toTrainingCamp();
		Coach coach = coachService.findById(dto.getCoachDto().getId());
		if (coach == null) {
			return new ResponseEntity<TrainingCampDTO>(HttpStatus.NOT_FOUND);
		}
		Court court = courtService.findById(dto.getCourtDto().getId());
		if (court == null) {
			return new ResponseEntity<TrainingCampDTO>(HttpStatus.NOT_FOUND);
		}

		tcamp.setCoach(coach);
		tcamp.setCourt(court);

		trainingCourtService.add(tcamp);
		TrainingCampDTO result = TrainingCampDTO.toTrainingCampDto(tcamp);
		UriComponents uriComponents = uriComponentsBuilder.path("/reservations/{id}").buildAndExpand(result.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(result);
		// return new ResponseEntity<ReservationInputDTO>(result, HttpStatus.CREATED);
	}

	@PutMapping("/training-camp/{id}")
	public ResponseEntity<TrainingCampDTO> update(@PathVariable int id, @RequestBody TrainingCampDTO dto) {
		logger.info("calling update reservation method");
		if (dto.getId() != id) {
			throw new BadRequestException("bad input data: id parameter must be equal to reservation id");
		}
		TrainingCamp res = dto.toTrainingCamp();
		TrainingCamp found = trainingCourtService.findById(id);
		if (found == null) {
			throw new ResourceNotFoundException("training camp not found");
		}
		Coach coach = coachService.findById(dto.getCoachDto().getId());
		if (coach == null) {
			throw new ResourceNotFoundException("coach not found");
		}
		Court court = courtService.findById(dto.getCourtDto().getId());
		if (court == null) {
			throw new ResourceNotFoundException("court not found");
		}

		res.setCoach(coach);
		res.setCourt(court);
		
		trainingCourtService.update(res);
		TrainingCampDTO result = TrainingCampDTO.toTrainingCampDto(res);
		return new ResponseEntity<TrainingCampDTO>(result, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/training-camp/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		logger.info("calling delete reservation method");
		TrainingCamp found = trainingCourtService.findById(id);
		if (found == null) {
			throw new ResourceNotFoundException("training camp not found");
		}
		trainingCourtService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
