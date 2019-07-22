package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.TrainingCampDAO;
import it.capgemini.clubOlympia.abstraction.service.TrainingCampService;
import it.capgemini.clubOlympia.entities.TrainingCamp;

@Service
public class TrainingCourtServiceStandard implements TrainingCampService {

	@Autowired
	TrainingCampDAO trainingCourtDao;
	
	@Override
	@Transactional
	public Iterable<TrainingCamp> allTrainingCamp() {
		return trainingCourtDao.allTrainingCamp();
	}

	@Override
	@Transactional
	public TrainingCamp add(TrainingCamp newTrainingCourt) {
		return trainingCourtDao.add(newTrainingCourt);
	}

	@Override
	@Transactional
	public TrainingCamp update(TrainingCamp newTrainingCourt) {
		return trainingCourtDao.update(newTrainingCourt);
	}

	@Override
	@Transactional
	public TrainingCamp delete(int idTrainingCourt) {
		return trainingCourtDao.delete(idTrainingCourt);
	}

	@Override
	@Transactional
	public TrainingCamp findById(int id) {
		return trainingCourtDao.findById(id);
	}

}
