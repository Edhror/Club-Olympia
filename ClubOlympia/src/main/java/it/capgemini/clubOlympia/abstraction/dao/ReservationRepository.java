package it.capgemini.clubOlympia.abstraction.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.capgemini.clubOlympia.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	Iterable<Reservation> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

	Iterable<Reservation> findByStartTimeBetweenOrEndTimeBetween(LocalDateTime beginStartTime, LocalDateTime beginEndTime,
			LocalDateTime endStartTime, LocalDateTime endEndTime);

	Iterable<Reservation> findByTimeRange(@Param("startRange") LocalDateTime startRange,@Param("endRange") LocalDateTime endRange);
	
	Iterable<Reservation> findByCourtId(int courtId);
	
	Iterable<Reservation> findByTimeRangeForTennis(LocalDateTime startRange, LocalDateTime endRange);
	
	Iterable<Reservation> findByTimeRangeForSoccer(LocalDateTime startRange, LocalDateTime endRange);
}
