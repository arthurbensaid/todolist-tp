//package fr.sij.tp.repository;
//
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Repository;
//
//import fr.sij.tp.entity.GenericEntity;
//import fr.sij.tp.entity.TodoList;
//
//@Repository
//public abstract class GenericEntityRepository<T> extends SimpleJpaRepository<T, Integer> {
//
//	
//	public GenericEntityRepository(Class<T> domainClass, EntityManager em) {
//		super(domainClass, em);
//	}
//
//	public T getById(int id) {
//		Optional<T> opt = this.findById(id);
//		if(opt.isPresent()) {
//			T t = opt.get();
//			return t;
//		}else {
//			return null;
//		}
//	}
//}
