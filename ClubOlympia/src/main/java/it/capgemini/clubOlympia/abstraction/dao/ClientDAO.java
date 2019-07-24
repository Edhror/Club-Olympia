package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Client;

public interface ClientDAO extends CrudDAO<Client>{
//   Client findById(int id);
//   Client add(Client client); 
//   Iterable<Client> all();
   
   Iterable<Client> findByTrainingCamp(int campId);
   Iterable<Client> findByNotInTrainingCamp(int campId);
}
