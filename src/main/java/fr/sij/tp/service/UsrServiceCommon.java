//package fr.sij.tp.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import fr.sij.tp.entity.Usr;
//import fr.sij.tp.repository.CommonRepository;
//
//@Service
//public class UsrServiceCommon {
//	
//	@Autowired CommonRepository<Usr> repo;
//	
//	public Usr getById(int id) {
//		return repo.getById(id);
//	}
//	
//	public List<Usr> getAll() {
//		return repo.findAll();
//	}
//	
//	public int create(Usr usr) {
//		Usr updatedUsr = repo.save(usr);
//		return updatedUsr.id;
//	}
//
//	public void remove(int id) {
//		repo.deleteById(id);
//		
//	}
//}
