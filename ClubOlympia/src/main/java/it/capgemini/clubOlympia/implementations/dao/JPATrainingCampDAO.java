package it.capgemini.clubOlympia.implementations.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.TrainingCampDAO;
import it.capgemini.clubOlympia.entities.Client;
import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

@Repository
public class JPATrainingCampDAO implements TrainingCampDAO {

	public static final String HQL_SELECT_ALL = "from TrainingCamp tc";
    public static final String HQL_SELECT_BY_TIME_AND_SPORT = HQL_SELECT_ALL + 
    						" where tc.start >= :start and tc.end <= :end and tc.tipoSport = :tipoSport";
	@Autowired
	EntityManager manager;

	@Override
	public Iterable<TrainingCamp> allTrainingCamp() {
		List<TrainingCamp> res = manager.createQuery(HQL_SELECT_ALL, TrainingCamp.class).getResultList();
		return res;
	}

	@Override
	public TrainingCamp add(TrainingCamp newTrainingCamp) {
		manager.persist(newTrainingCamp);
		return newTrainingCamp;
	}

	@Override
	public TrainingCamp update(TrainingCamp newTrainingCamp) {
		manager.merge(newTrainingCamp);
		return newTrainingCamp;
	}

	@Override
	public TrainingCamp delete(int idTrainingCamp) {
		TrainingCamp found = manager.getReference(TrainingCamp.class, idTrainingCamp);
		manager.remove(found);
		return found;
	}

	@Override
	public TrainingCamp findById(int id) {
		TrainingCamp found = manager.find(TrainingCamp.class, id);
		return found;
	}

	@Override
	public Iterable<TrainingCamp> byTimeRangeAndSport(LocalDateTime start, LocalDateTime end, TipoSport tipoSport) {
		List<TrainingCamp> res = manager.createQuery(HQL_SELECT_BY_TIME_AND_SPORT, TrainingCamp.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.setParameter("tipoSport", tipoSport)
				.getResultList();
		return res;
	}

	@Override
	public void changeEnrollment(int trainingCampId, Client client, boolean enroll) {
		TrainingCamp camp = this.findById(trainingCampId);
		if(enroll) {
			camp.enroll(client);
		} else {
			camp.cancelEnrollment(client);
		}
	}

}
