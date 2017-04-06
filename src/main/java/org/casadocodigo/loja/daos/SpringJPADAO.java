package org.casadocodigo.loja.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.casadocodigo.loja.models.IEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class SpringJPADAO<T extends Serializable> implements DAO<T> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> classe;

	public SpringJPADAO() {
	}

	public SpringJPADAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(T t) {
		 entityManager.persist(t);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addAll(List<T> list) {
		for (T t : list)
			entityManager.persist(t);
	}
	
  public List<T> findObjectsPages(int startingAt, int maxPerPage) {
 
        Query query = entityManager.createQuery("select p from "+classe.getName()+" p");
        query.setFirstResult(startingAt);
        query.setMaxResults(maxPerPage);
 
        return query.getResultList();
    }

  public int countObjectsTotal() {
     
      Query query = entityManager.createQuery("select COUNT(p) from "+classe.getName()+" p");

      Number result = (Number) query.getSingleResult();

      return result.intValue();
  }     
  
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAll() {

		Query query = entityManager.createQuery("SELECT e from "+classe.getName()+" e");

		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(IEntity entity ) {
		entityManager.remove(entityManager.getReference(classe,entity.getId()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll(List<T> list) {
		for (T t : list)
			entityManager.refresh(t);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
    public void truncate (Class<T> classe) {
		Query query = entityManager
				.createNativeQuery("TRUNCATE TABLE web_perfil_colaborador");
		query.executeUpdate();
	}
	@Override
	public void setPersistenceManager(Object object) {
		this.entityManager = (EntityManager) object;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public T update(T t) {
		T atualizado =entityManager.merge(t);
     	return atualizado;
	}

	@Override
	public DAO<T> getInstance(Class<T> classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T find(int id) {
		return entityManager.find(classe, id);
	}

	@Override
	public void setParameterClass(Class<T> classe) {
		this.classe = classe;
	}

	@Override
	public javax.persistence.Query Query(String query) {
		return entityManager.createQuery(query);
	}

	public List<T> findObjectsPagesForName(Query query,
		int valorInicial, int maxPorPagina) {
		 query.setFirstResult(valorInicial);
	     query.setMaxResults(maxPorPagina);
		
		return query.getResultList();
	}
}
