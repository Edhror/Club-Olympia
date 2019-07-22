package it.capgemini.clubOlympia.abstraction.service;

import it.capgemini.clubOlympia.entities.Team;

public interface TeamService {
	Iterable<Team> list();
	void save(Team challenge);
	void update(Team challenge);
	void delete(int id);
    Team byId(int id);
}
