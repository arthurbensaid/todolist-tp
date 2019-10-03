package fr.sij.tp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.sij.tp.entity.GenericEntity;

public class CommonRepository<T extends GenericEntity> extends SimpleJpaRepository<T, Integer> {
	protected final EntityManager em;
	protected final Class<T> entityClass;

	public CommonRepository(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.em = entityManager;
		this.entityClass = domainClass;
	}
	
	@Transactional
	public void persist(T entity) {
		em.persist(entity);
	}
	
	public void remove(T entity) {
		em.remove(entity);
	}
	
	public T merge(T entity) {
		return em.merge(entity);
	}
	
	public T getById(Integer id) {
		return em.find(entityClass, id);
	}

}
