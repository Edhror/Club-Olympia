package it.capgemini.clubOlympia.abstraction.service;

import it.capgemini.clubOlympia.entities.Reservation;

public interface ReservationService {
	Iterable<Reservation> list();

	void save(Reservation reservation);

	void update(Reservation reservation);
	
	Reservation byId(int id);

	void delete(int id);
}
