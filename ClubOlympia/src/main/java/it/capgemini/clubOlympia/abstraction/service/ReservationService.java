package it.capgemini.clubOlympia.abstraction.service;

import java.time.LocalDateTime;

import it.capgemini.clubOlympia.entities.Reservation;

public interface ReservationService {
	Iterable<Reservation> list();

	Iterable<String> save(Reservation reservation);
	
	Reservation byId(int id);

	void delete(int id);
	
	Iterable<Reservation> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
}
