package fr.sij.tp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.Task;

@Repository
@Transactional
public class TaskRepository extends CommonRepository<Task> {

	public TaskRepository(EntityManager em) {
		super(Task.class, em);
		// TODO Auto-generated constructor stub
	}

}
