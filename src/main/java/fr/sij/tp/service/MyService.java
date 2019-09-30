package fr.sij.tp.service;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sij.tp.dao.MyDao;

@Service
public class MyService {

	@Autowired private MyDao myDao;
	
	public MyService() {
		System.out.println("mon service: "+myDao);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("mon init: "+myDao);
		myDao.fonctionToto();
	}

}
