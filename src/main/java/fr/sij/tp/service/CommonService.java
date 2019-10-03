package fr.sij.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import fr.sij.tp.entity.GenericEntity;
import fr.sij.tp.repository.CommonRepository;


public abstract class CommonService<T extends GenericEntity> {
	
	private CommonRepository<T> repo;
	
	@Autowired
	public final void setRepository(CommonRepository<T> repo) {
		this.repo = repo;
	}

	public T getById(int id) {
		return repo.getById(id);
		
	}
	
	public List<T> getAll() {
		return repo.findAll();
	}
	
	public T create(T entity) {
		T updatedEntity = repo.save(entity);
		return updatedEntity;
	}

	public void remove(int id) {
		repo.deleteById(id);
		
	}
	
	public T update(T entity) {
		return repo.merge(entity);
	}
}
