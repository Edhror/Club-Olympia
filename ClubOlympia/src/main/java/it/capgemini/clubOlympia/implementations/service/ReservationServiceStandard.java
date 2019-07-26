package it.capgemini.clubOlympia.implementations.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.abstraction.dao.CrudDAO;
import it.capgemini.clubOlympia.abstraction.dao.ReservationRepository;
import it.capgemini.clubOlympia.abstraction.service.ReservationService;
import it.capgemini.clubOlympia.entities.Court;
import it.capgemini.clubOlympia.entities.Reservation;
import it.capgemini.clubOlympia.entities.SoccerCourt;
import it.capgemini.clubOlympia.entities.TennisCourt;

@Service
public class ReservationServiceStandard implements ReservationService {

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
	public Iterable<String> save(Reservation reservation) {
//		reservationDao.add(reservation);

		List<Reservation> found = new ArrayList<Reservation>();
		String type = "";

		Court court = courtDao.findById(reservation.getCourt().getId(), Court.class);
		if (court instanceof TennisCourt) {
			found = (List) reservationRepo.findByTimeRangeForTennis(reservation.getEndTime(),reservation.getStartTime());
			type = "tennis";
		} else if(court instanceof SoccerCourt){
			found = (List) reservationRepo.findByTimeRangeForSoccer(reservation.getEndTime(),reservation.getStartTime());
			type = "soccer";
		}

		List<String> time;

		if (found.isEmpty()) {
			reservationRepo.save(reservation);
			return null;
		} else {
			time = new ArrayList<String>();

			LocalDateTime start = reservation.getStartTime();
			LocalDateTime end = reservation.getEndTime();

			int def = end.getHour() - start.getHour();
			start = start.withHour(0);
			end = end.withHour(def);
			
			if (type == "tennis") {
				
				while (end.getDayOfYear() == reservation.getEndTime().getDayOfYear()) {
					List<Reservation> res = (List) reservationRepo.findByTimeRangeForTennis(end, start);
					if (res.isEmpty()) {
						time.add("start: " + start.toString() + " end: " + end.toString());
					}
					start = start.plusHours(1);
					end = end.plusHours(1);
				}
				
			} else if(type == "soccer") {
				
				while (end.getDayOfYear() == reservation.getEndTime().getDayOfYear()) {
					List<Reservation> res = (List) reservationRepo.findByTimeRangeForSoccer(end, start);
					if (res.isEmpty()) {
						time.add("start: " + start.toString() + " end: " + end.toString());
					}
					start = start.plusHours(1);
					end = end.plusHours(1);
				}
			}
		}

		return time;
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
	public Iterable<Reservation> findByTimeRange(LocalDateTime startRange, LocalDateTime endRange) {
		// return reservationRepo.findByStartBetween(startTime, endTime);
		// return reservationRepo.findByStartBetweenOrEndBetween(startTime, endTime,
		// startTime, endTime);
//		return reservationRepo.findByCourtId(1);
//		return reservationRepo.findByTimeRangeForTennis(startRange, endRange);
//		return reservationRepo.findByTimeRangeForSoccer(startRange, endRange);

		return reservationRepo.findByTimeRange(startRange, endRange);

	}

}
