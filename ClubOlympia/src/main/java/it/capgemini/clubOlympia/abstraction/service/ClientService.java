package it.capgemini.clubOlympia.abstraction.service;

import it.capgemini.clubOlympia.entities.Client;

public interface ClientService {
    Client findById(int id);
    
}
