package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Team;

public interface TeamDAO {
	Iterable<Team> allTeams();
	Team add(Team newChallenge);
	Team update(Team newChallenge);
	Team delete(int idChallenge);
	Team findById(int id);
}
