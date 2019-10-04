package fr.sij.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.sij.tp.entity.Usr;

public interface UsrRepositoryQueries extends JpaRepository<Usr, Integer> { 
	
	@Query("SELECT u FROM Usr u WHERE u.login LIKE :prefix%") // en SQL where login like 'xxxx%'
	public List<Usr> findUserByPrefix(@Param("prefix") String prefix);

	@Query(value="SELECT * FROM USR WHERE FIRSTNAME LIKE %?1% OR LASTNAME LIKE %?1%", nativeQuery = true)
	public List<Usr> findUserByName(String str);

	public List<Usr> findByLowPassword();
	
	public int countUsers();
	
	@Query(value="SELECT u FROM Usr WHERE u.login = :login")
	public Usr findByLogin(@Param("login") String login);
}
