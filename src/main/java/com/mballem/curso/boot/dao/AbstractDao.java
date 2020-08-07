package com.mballem.curso.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractDao <T, PK extends Serializable>{
	
	//Pega a Entidade a partir da assinatura da classe AbstractDao
	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	private EntityManager entityManager;//Aqui o spring injeta esse objeto entityManager da JPA
	
	protected EntityManager getEntityManager() {//protegida pois esses método so sera acessao a quem implementa essa classe
		return entityManager;
	}
	
	public void save(T entity) {
		entityManager.persist(entity);
	}
	
	public void update(T entity) {
		entityManager.merge(entity);
	}
	
	public void delete(PK id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll(){
		return entityManager.createQuery("FROM "+ entityClass.getSimpleName() + " ORDER BY id", entityClass).getResultList();
	}
	
	//Facilita varios tipos de consulta
	protected List<T> createQuery(String jpql, Object... params){//jpql como parametro, params valor do parametro
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
