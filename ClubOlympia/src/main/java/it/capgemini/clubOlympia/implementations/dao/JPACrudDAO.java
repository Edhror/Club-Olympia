package it.capgemini.clubOlympia.implementations.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.capgemini.clubOlympia.abstraction.dao.CrudDAO;

@Repository
public class JPACrudDAO<T> implements CrudDAO<T> {

	public static final String ALL_ITEMS = "from ";
	

	
	@Autowired
	EntityManager manager;
	
	@Override
	public Iterable<T> all(Class<T> entityClass) {
		@SuppressWarnings("unchecked")
		List<T> res = manager.createQuery(ALL_ITEMS+entityClass.getSimpleName()).getResultList();

		return res;
	}

	@Override
	public T add(T newItem) {
		manager.persist(newItem);
		return newItem;
	}

	@Override
	public T update(T newItem) {
		manager.merge(newItem);
		return newItem;
	}

	
	public T delete(int idItem, Class<T> entityClass) {
		T found = manager.getReference(entityClass, idItem);
		manager.remove(found);
		return found;
	}

	@Override
	public T findById(int id, Class<T> entityClass) {
		T found = manager.find(entityClass, id);
		return found;
	}

}
