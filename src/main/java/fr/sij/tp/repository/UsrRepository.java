package fr.sij.tp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.Usr;

@Repository
@Transactional
public class UsrRepository extends GenericEntityRepository<Usr> { 

	public UsrRepository(EntityManager em) {
		super(Usr.class,em);
	}

}
