package fr.sij.tp.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.Task;

@Repository
public class TaskRepository extends GenericEntityRepository<Task> {

	public TaskRepository(EntityManager em) {
		super(Task.class, em);
		// TODO Auto-generated constructor stub
	}

}
