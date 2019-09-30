package fr.sij.tp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.MyCache;
import fr.sij.tp.dao.MyDao;

@Service
public class MyServiceA {
	
	@Autowired private MyDao myDao;
	@Autowired private MyCache myCache;

	public MyServiceA() {
		System.out.println("Ding Dang "+myDao);
		System.out.println("Où sont les steaks ? "+myCache);
	}

}
