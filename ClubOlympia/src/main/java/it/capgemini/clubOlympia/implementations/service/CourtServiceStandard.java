package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.abstraction.service.CourtService;
import it.capgemini.clubOlympia.entities.Court;

@Service
public class CourtServiceStandard implements CourtService{
  
	@Autowired
	private CourtDAO courtDao;

	@Override
	@Transactional
	public Court findById(int id) {
		return courtDao.findById(id);
		
	}
	
	
	
	
}
