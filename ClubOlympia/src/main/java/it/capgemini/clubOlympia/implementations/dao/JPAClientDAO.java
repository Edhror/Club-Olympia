package it.capgemini.clubOlympia.implementations.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.TrainingCamp;

@Repository
public class JPAClientDAO implements ClientDAO {

	public static final String ALL_CLIENTS = "select c from Client as c ";
	
	public static final String CLIENTS_NOT_IN_TRAININGCAMPS = ALL_CLIENTS + 
			"left join c.trainingcamps as t where t.id != :id or t.id is null";
	
	@Autowired
	protected EntityManager manager;

	@Override 
	public Client findById(int id) {
		Client found = manager.find(Client.class, id);
		return found;

	}

	@Override
	public Client save(Client client) {
		manager.persist(client);
		return client;
	}

	@Override
	public Iterable<Client> findByTrainingCamp(int campId) {
		return manager.find(TrainingCamp.class, campId).getClients();
	}

	@Override
	public Iterable<Client> all() {
		return manager.createQuery(ALL_CLIENTS, Client.class).getResultList();
	}

	@Override
	public Iterable<Client> findByNotInTrainingCamp(int campId) {
		return manager.createQuery(CLIENTS_NOT_IN_TRAININGCAMPS, Client.class)
				.setParameter("id", campId)
				.getResultList();
	}

}
