package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Coach;

public interface CoachDAO {
	   Coach findById(int id);
	   Coach save(Coach coach);
}
