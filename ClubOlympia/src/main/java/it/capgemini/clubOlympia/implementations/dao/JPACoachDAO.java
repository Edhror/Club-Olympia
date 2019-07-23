package it.capgemini.clubOlympia.implementations.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.CoachDAO;
import it.capgemini.clubOlympia.entities.Coach;

@Repository
public class JPACoachDAO implements CoachDAO {

	public static final String ALL_COACHES = "from Coach as c";
	
	@Autowired
	protected EntityManager manager;

	@Override
	public Coach findById(int id) {
		Coach found = manager.find(Coach.class, id);
		return found;
	}

	@Override
	public Coach save(Coach coach) {
		manager.persist(coach);
		return coach;
	}

	@Override
	public Iterable<Coach> all() {
		return manager.createQuery(ALL_COACHES, Coach.class).getResultList();
	}

}
