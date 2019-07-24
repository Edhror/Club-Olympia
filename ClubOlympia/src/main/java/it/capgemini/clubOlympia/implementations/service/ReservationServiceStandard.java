package it.capgemini.clubOlympia.implementations.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.abstraction.dao.CrudDAO;
import it.capgemini.clubOlympia.abstraction.dao.ReservationRepository;
import it.capgemini.clubOlympia.abstraction.service.ReservationService;
import it.capgemini.clubOlympia.entities.Reservation;

@Service
public class ReservationServiceStandard  implements ReservationService{

	@Autowired
	CrudDAO<Reservation> reservationDao;
	@Autowired
	ClientDAO clientDao;
	@Autowired
	CourtDAO courtDao;
	@Autowired
	ReservationRepository reservationRepo;
	
	@Override
	@Transactional
	public Iterable<Reservation> list() {
//		return reservationDao.all(Reservation.class);
		return reservationRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Reservation reservation) {
//		reservationDao.add(reservation);
		reservationRepo.save(reservation);
		
	}

	@Override
	@Transactional
	public Reservation byId(int id) {
//		return reservationDao.findById(id, Reservation.class);
		return reservationRepo.getOne(id);
	}

	@Override
	@Transactional
	public void delete(int id) {
//		reservationDao.delete(id, Reservation.class);
		reservationRepo.deleteById(id);
		
	}

	@Override
	@Transactional
	public Iterable<Reservation> findByTimeRanged(LocalDateTime startTime, LocalDateTime endTime) {
		//return reservationRepo.findByStartBetween(startTime, endTime);
		return reservationRepo.findByStartBetweenOrEndBetween(startTime, endTime, startTime, endTime);
	}
	
}
