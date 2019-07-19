package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.dao.TeamDAO;
import it.capgemini.clubOlympia.abstraction.service.TeamService;
import it.capgemini.clubOlympia.entities.Team;

@Service
public class TeamServiceStandard implements TeamService{

	@Autowired
	private TeamDAO teamDao;
	@Autowired
	ClientDAO clientDao;
	
	@Override
	@Transactional
	public Team byId(int id) {
		return teamDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Team> list() {
		return teamDao.allTeams();
	}

	@Override
	@Transactional
	public void save(Team team) {
		teamDao.add(team);
	}

	@Override
	@Transactional
	public void update(Team team) {
		teamDao.update(team);
	}

	@Override
	@Transactional
	public void delete(int id) {
		teamDao.delete(id);
	}

}
