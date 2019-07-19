package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Client;

public interface ClientDAO {
   Client findById(int id);
}
