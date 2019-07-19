package it.capgemini.clubOlympia.implementations.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.entities.Client;

@Repository
public class JPAClientDAO implements ClientDAO {

	@Autowired
	private EntityManager manager;

	@Override 
	public Client findById(int id) {
		Client found = manager.find(Client.class, id);
		return found;

	}

}
