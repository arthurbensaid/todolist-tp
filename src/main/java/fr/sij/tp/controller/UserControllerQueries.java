//package fr.sij.tp.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import fr.sij.tp.entity.Usr;
//import fr.sij.tp.repository.UsrRepositoryQueries;
//
//@RestController
//@RequestMapping("users")
//public class UserController {
//	
//	@Autowired UsrRepositoryQueries repo;
//	
//	@GetMapping("/prefix/{prefix}")
//	public List<Usr> findUserByPrefix(@PathVariable String prefix) {
//		return repo.findUserByPrefix(prefix);
//	}
//	
//	@GetMapping("/name/{str}")
//	public List<Usr> findUserByName(@PathVariable String str) {
//		return repo.findUserByName(str);
//	}
//	
//	@GetMapping("/password")
//	public List<Usr> minLengthPassword(){
//		return repo.findByLowPassword();
//	}
//	
//	@GetMapping("/count")
//	public int countUsers(){
//		return repo.countUsers();
//	}
//}