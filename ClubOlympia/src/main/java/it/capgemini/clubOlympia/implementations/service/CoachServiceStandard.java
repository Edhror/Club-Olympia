package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.CoachDAO;
import it.capgemini.clubOlympia.abstraction.service.CoachService;
import it.capgemini.clubOlympia.entities.Coach;

@Service
public class CoachServiceStandard implements CoachService{

	@Autowired
	CoachDAO coachDao;

	@Override
	@Transactional
	public Coach findById(int id) {
		return coachDao.findById(id);
	}

	@Override
	public Iterable<Coach> all() {
		return coachDao.all();
	}
	
	
}
