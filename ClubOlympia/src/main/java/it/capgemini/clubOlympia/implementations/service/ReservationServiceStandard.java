package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.abstraction.dao.ReservationDAO;
import it.capgemini.clubOlympia.abstraction.service.ReservationService;
import it.capgemini.clubOlympia.entities.Reservation;

@Service
public class ReservationServiceStandard  implements ReservationService{

	@Autowired
	ReservationDAO reservationDao;
	@Autowired
	ClientDAO clientDao;
	@Autowired
	CourtDAO courtDao;
	
	@Override
	@Transactional
	public Iterable<Reservation> list() {
		return reservationDao.allReservations();
	}

	@Override
	@Transactional
	public void save(Reservation reservation) {
		reservationDao.add(reservation);
		
	}

	@Override
	@Transactional
	public Reservation byId(int id) {
		return reservationDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		reservationDao.delete(id);
		
	}

	@Override
	@Transactional
	public void update(Reservation reservation) {
		reservationDao.update(reservation);
		
	}

}
