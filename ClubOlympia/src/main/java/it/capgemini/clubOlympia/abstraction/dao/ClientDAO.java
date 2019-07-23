package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Client;

public interface ClientDAO {
   Client findById(int id);
   Client save(Client client);
   Iterable<Client> findByTrainingCamp(int campId);
   Iterable<Client> all();
   Iterable<Client> findByNotInTrainingCamp(int campId);
}
