package fr.sij.tp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.sij.tp.entity.Task;
import fr.sij.tp.repository.CommonRepository;

@Service
public class TaskService {
	
	@Autowired CommonRepository<Task> repo;
	
	public Task getById(int id) {
		return repo.getById(id);
	}
	
	public List<Task> getAll() {
		return repo.findAll();
	}
	
	public int create(Task task) {
		Task updatedTask = repo.save(task);
		return updatedTask.id;
	}

	public void remove(int id) {
		repo.deleteById(id);
		
	}

}
