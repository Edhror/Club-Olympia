package it.capgemini.clubOlympia.abstraction.service;

import it.capgemini.clubOlympia.entities.Client;

public interface ClientService {
    Client findById(int id);
    Iterable<Client> findByTrainingCamp(int campId);
    Iterable<Client> all();
    Iterable<Client> findByNotInTrainingCamp(int campId);
}
