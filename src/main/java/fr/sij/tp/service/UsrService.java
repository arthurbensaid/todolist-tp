package fr.sij.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.sij.tp.entity.Usr;
import fr.sij.tp.repository.UsrRepository;
import fr.sij.tp.repository.UsrRepositoryQueries;

@Service
public class UsrService implements UserDetailsService {
	
	@Autowired UsrRepository repo;
	@Autowired UsrRepositoryQueries repoQueries;
	
	public Usr getById(int id) {
		return repo.getById(id);
	}
	
	public List<Usr> getAll() {
		return repo.findAll();
	}
	
	public int create(Usr usr) {
		Usr updatedUsr = repo.save(usr);
		return updatedUsr.id;
	}

	public void remove(int id) {
		repo.deleteById(id);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usr u = repoQueries.findByLogin(username);
		if(u==null) throw new UsernameNotFoundException(username+" not found");
		return User.withUsername(username).password(u.password).roles("admin").build();
	}
}
