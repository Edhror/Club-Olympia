package it.capgemini.clubOlympia.implementations.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.ChallengeDAO;
import it.capgemini.clubOlympia.entities.Challenge;

@Repository
public class JPAChallengeDAO implements ChallengeDAO {

	public static final String HQL_SELECT_ALL = "from Challenge C";

	@Autowired
	private EntityManager manager;

	@Override
	public Iterable<Challenge> allChallenge() {
		List<Challenge> cha = manager.createQuery(HQL_SELECT_ALL,
				Challenge.class).getResultList();
		return cha;
	}

	@Override
	public Challenge add(Challenge newChallenge) {
		manager.persist(newChallenge);
		return newChallenge;
	}

	@Override
	public Challenge update(Challenge newChallenge) {
		manager.merge(newChallenge);
		return newChallenge;
	}

	@Override
	public Challenge delete(int idChallenge) {
		Challenge found = manager.getReference(Challenge.class, idChallenge);
		manager.remove(found);
		return found;
	}

	@Override
	public Challenge findById(int id) {
		Challenge found = manager.find(Challenge.class, id);
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Challenge> findByDate(LocalDateTime date) {
		LocalDateTime end = date.plusDays(1);
		List<Challenge> found = manager.createQuery(HQL_SELECT_ALL +
				" where C.reservation.start >= :start and C.reservation.start < : end")
				.setParameter("start", date).setParameter("end", end).getResultList();
		return found;
	}


}
