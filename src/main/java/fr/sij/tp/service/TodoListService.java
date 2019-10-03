package fr.sij.tp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.sij.tp.entity.TodoList;
import fr.sij.tp.repository.CommonRepository;

@Service
public class TodoListService {
	
	@Autowired CommonRepository<TodoList> repo;
	
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
