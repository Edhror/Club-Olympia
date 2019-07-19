package it.capgemini.clubOlympia.abstraction.service;

import java.time.LocalDateTime;
import java.util.List;

import it.capgemini.clubOlympia.entities.Challenge;

public interface ChallengeService {
	Iterable<Challenge> list();

	void save(Challenge challenge);

	void update(Challenge challenge);
	
	Challenge byId(int id);

	void delete(int id);

	List<Challenge> byDate(LocalDateTime date);
}
