package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Court;

public interface CourtDAO {
     Court findById(int id);
     Court save(Court court);
}
