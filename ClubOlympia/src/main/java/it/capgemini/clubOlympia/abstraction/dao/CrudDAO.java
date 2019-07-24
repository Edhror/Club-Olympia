package it.capgemini.clubOlympia.abstraction.dao;

public interface CrudDAO<T> {

    Iterable<T> all(Class<T> entityClass);
    T add(T newItem);
    T update(T newItem);
    T delete (int idItem, Class<T> entityClass);
    T findById(int id, Class<T> entityClass);
}
