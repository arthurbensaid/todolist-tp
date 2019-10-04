package fr.sij.tp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.Usr;

@Repository
@Transactional
public class UsrRepository extends CommonRepository<Usr> { 

	public UsrRepository(EntityManager em) {
		super(Usr.class,em);
	}
	
	public Usr findByLogin(String login) { // Moins efficace que la requête JPQL car ne passe pas par un index
		for(Usr user:this.findAll()) {
			if(user.login.equals(login)) return user;
		}
		return null;
	}

}
