package fr.sij.tp.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.Usr;

@Repository
public class UsrRepository extends GenericEntityRepository<Usr> { 

	public UsrRepository(EntityManager em) {
		super(Usr.class,em);
	}

}
