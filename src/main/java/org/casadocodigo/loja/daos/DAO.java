package org.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.casadocodigo.loja.models.IEntity;


public interface DAO <T> {
	
	DAO<T> getInstance (Class<T> classe);
	void add (T elemento);
	void  addAll (List <T> t);
	T update(T t);
    Query Query(String query);
    List<Object> getAll();
    void remove (IEntity t );
    void removeAll(List<T> list);
    void truncate (Class <T> t);
    void setParameterClass(Class<T> classe);
    void setPersistenceManager(Object object);
    List<T> findObjectsPages(int startingAt, int maxPerPage);
    int countObjectsTotal();
    T find(int id);
	EntityManager getEntityManager();
}
