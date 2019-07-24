package it.capgemini.clubOlympia.abstraction.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import it.capgemini.clubOlympia.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	Iterable<Reservation> findByStartBetween(LocalDateTime startTime, LocalDateTime endTime);

	Iterable<Reservation> findByStartBetweenOrEndBetween(LocalDateTime beginStartTime, LocalDateTime beginEndTime,
			LocalDateTime endStartTime, LocalDateTime endEndTime);

}
