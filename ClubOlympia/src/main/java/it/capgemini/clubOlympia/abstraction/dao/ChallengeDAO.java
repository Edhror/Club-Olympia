package it.capgemini.clubOlympia.abstraction.dao;

import java.time.LocalDateTime;
import java.util.List;

import it.capgemini.clubOlympia.entities.Challenge;

public interface ChallengeDAO {
    Iterable<Challenge> allChallenge();
    Challenge add(Challenge newChallenge);
    Challenge update(Challenge newChallenge);
    Challenge delete (int idChallenge);
    Challenge findById(int id);
    List<Challenge> findByDate(LocalDateTime date);
}
