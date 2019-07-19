package it.capgemini.clubOlympia.implementations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capgemini.clubOlympia.abstraction.dao.ClientDAO;
import it.capgemini.clubOlympia.abstraction.service.ClientService;
import it.capgemini.clubOlympia.entities.Client;

@Service
public class ClientServiceStandard implements ClientService{

	@Autowired
	private ClientDAO clientDao;
	
	@Override
	@Transactional
	public Client findById(int id) {
		return clientDao.findById(id);
	}

}
