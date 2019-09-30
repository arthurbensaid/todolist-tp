package fr.sij.tp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.sij.tp.entity.TodoList;
import fr.sij.tp.repository.TodoListRepository;

@Service
public class TodoListService {
	
	@Autowired TodoListRepository repo;
	
	public void foo(int id) {
		// solution 1 à préférer avec les optional
		Optional<TodoList> opt = repo.findById(id);
		if(opt.isPresent()) {
			TodoList list = opt.get(); // Récupérer l'objet à partir de l'optional
		}else {
			// equivalent à findById return null
		}
		
		//solution 2 si on est certain que l'entité existe ou doit exister pour l'id donné
		TodoList list = repo.getOne(id);
		if(list==null) {
			// normalement le get One lève une exception s'il ne trouve pas l'id
		}else {
			
		}
	}
	
	public TodoList getById(int id) {
		return repo.getById(id);
	}
	
	public List<TodoList> getAll() {
		return repo.findAll();
	}
	
	public int create(TodoList list) {
		TodoList updatedList = repo.save(list);
		return updatedList.id;
	}

	public void remove(int id) {
		repo.deleteById(id);
		
	}

}
