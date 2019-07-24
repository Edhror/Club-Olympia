package it.capgemini.clubOlympia.implementations.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.CrudDAO;
import it.capgemini.clubOlympia.abstraction.dao.TrainingCampDAO;
import it.capgemini.clubOlympia.abstraction.service.TrainingCampService;
import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

@Service
public class TrainingCampServiceStandard implements TrainingCampService {

	@Autowired
	TrainingCampDAO trainingCampDao;
	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	CrudDAO<TrainingCamp> crudDao;
	
	@Override
	@Transactional
	public Iterable<TrainingCamp> allTrainingCamp() {
		return crudDao.all(TrainingCamp.class);
	}

	@Override
	@Transactional
	public TrainingCamp add(TrainingCamp newTrainingCourt) {
		return trainingCampDao.add(newTrainingCourt);
	}

	@Override
	@Transactional
	public TrainingCamp update(TrainingCamp newTrainingCourt) {
		return trainingCampDao.update(newTrainingCourt);
	}

	@Override
	@Transactional
	public TrainingCamp delete(int idTrainingCourt) {
		return trainingCampDao.delete(idTrainingCourt, TrainingCamp.class);
	}

	@Override
	@Transactional
	public TrainingCamp findById(int id) {
		return trainingCampDao.findById(id, TrainingCamp.class);
	}

	@Override
	@Transactional
	public Iterable<TrainingCamp> byTimeRangeAndSport(LocalDateTime start, LocalDateTime end, TipoSport tipoSport) {
		
		return trainingCampDao.byTimeRangeAndSport(start, end, tipoSport);
	}

	@Override
	@Transactional
	public void changeEnrollment(int trainingCampId, int clientId, boolean enroll) {
		Client client = clientDao.findById(clientId, Client.class);
		trainingCampDao.changeEnrollment(trainingCampId, client, enroll);
	}

}
