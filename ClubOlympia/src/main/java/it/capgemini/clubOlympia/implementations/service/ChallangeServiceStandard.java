package it.capgemini.clubOlympia.implementations.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ChallengeDAO;
import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.CourtDAO;
import it.capgemini.clubOlympia.abstraction.service.ChallengeService;
import it.capgemini.clubOlympia.entities.Challenge;

@Service
public class ChallangeServiceStandard implements ChallengeService{

	@Autowired
	ChallengeDAO challengeDao;
	@Autowired
	ClientDAO clientDao;
	@Autowired
	CourtDAO courtDao;
	
	@Override
	@Transactional
	public Iterable<Challenge> list() {
		return challengeDao.allChallenge();
	}

	@Override
	@Transactional
	public void save(Challenge challenge) {
		challengeDao.add(challenge);
	}

	@Override
	@Transactional
	public Challenge byId(int id) {
		return challengeDao.findById(id);
	}
	
	@Override
	@Transactional
	public List<Challenge> byDate(LocalDateTime date) {
		return challengeDao.findByDate(date);
	}

	@Override
	@Transactional
	public void delete(int id) {
		challengeDao.delete(id);
		
	}

	@Override
	@Transactional
	public void update(Challenge challenge) {
		challengeDao.update(challenge);
		
	}

}
