package fr.sij.tp.dao;

import org.springframework.stereotype.Repository;

@Repository

public class MyDao {
	
	public MyDao() {
		System.out.println("instanciation de "+this.getClass().getName());
	}
	
	public void fonctionToto() {
		System.out.println("Toto");
	}

}