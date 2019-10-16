package com.stefanini.hackaton.rest.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

public class GenericDAO<PK, E extends Serializable> {

	@PersistenceContext(unitName = "bancoHackatonUnit")
	private EntityManager em;

	private Class<E> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		Type genericSuperClass = getClass().getGenericSuperclass();

		ParameterizedType parametrizedType = null;

		while (parametrizedType == null) {
			if ((genericSuperClass instanceof ParameterizedType)) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>) genericSuperClass)
								.getGenericSuperclass();
			}
		}

		this.clazz = (Class<E>) parametrizedType.getActualTypeArguments()[1];
	}

	public List<E> list() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(clazz);
		query.from(clazz);
		List<E> resultList = em.createQuery(query).getResultList();
		return resultList;
	}

	public E findById(PK pk) {
		return em.find(clazz, pk);
	}

	@Transactional
	public void insert(E e) {
		em.persist(e);
	}

	@Transactional
	public E update(E e) {
		return em.merge(e);
	}

	@Transactional
	public void remove(E e) {
		em.remove(e);
	}

	public EntityManager getEntityManager() {
		return em;
	}

}