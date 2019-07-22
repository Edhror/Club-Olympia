package it.capgemini.clubOlympia.implementations.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.TeamDAO;
import it.capgemini.clubOlympia.entities.Team;

@Repository
public class JPATeamDAO implements TeamDAO {

	public static final String HQL_SELECT_ALL = "from Team C";

	@Autowired
	private EntityManager manager;

	@Override
	public Iterable<Team> allTeams() {
		List<Team> squad = manager.createQuery(HQL_SELECT_ALL, Team.class)
				                                  .getResultList();
		return squad;
	}

	@Override
	public Team add(Team newTeam) {
		manager.persist(newTeam);
		return newTeam;
	}

	@Override
	public Team update(Team newTeam) {
		manager.merge(newTeam);
		return newTeam;
	}

	@Override
	public Team delete(int idTeam) {
		Team found = manager.getReference(Team.class, idTeam);
		manager.remove(found);
		return found;
	}

	@Override
	public Team findById(int id) {
		Team found = manager.find(Team.class, id);
		return found;
	}

}
