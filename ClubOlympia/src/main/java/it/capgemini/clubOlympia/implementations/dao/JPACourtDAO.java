package it.capgemini.clubOlympia.implementations.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.entities.Court;

@Repository
public class JPACourtDAO implements CourtDAO {

	@Autowired
	protected EntityManager manager;
	
	@Override
	public Court findById(int id) {
		Court found = manager.find(Court.class, id);
		return found;

	}

	@Override
	public Court save(Court court) {
		manager.persist(court);
		return court;
	}

}
