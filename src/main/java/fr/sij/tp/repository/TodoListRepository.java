package fr.sij.tp.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.sij.tp.entity.TodoList;

@Repository
@Transactional
public class TodoListRepository extends GenericEntityRepository<TodoList> {

	public TodoListRepository(EntityManager em) {
		super(TodoList.class, em);
	}

}
