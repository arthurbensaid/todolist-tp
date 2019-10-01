package fr.sij.tp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sij.tp.dto.UsrDto;
import fr.sij.tp.entity.Usr;
import fr.sij.tp.service.TodoListService;
import fr.sij.tp.service.UsrService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired UsrService serv;
	@Autowired TodoListService listService;
	
	
	@GetMapping("")
	public List<UsrDto> getAll() {
		List<Usr> listAll = serv.getAll();
		List<UsrDto> displayList = new ArrayList<UsrDto>();
		
		for (Usr usr:listAll) {
			UsrDto dto = new UsrDto(usr);
			displayList.add(dto);
		}
		
		return displayList;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsrDto> getById(@PathVariable int id) {
		Usr usr = serv.getById(id);
		if (usr==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UsrDto(usr), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteUsr(@PathVariable int id) {
		Usr usr = serv.getById(id);
		if (usr==null) return new ResponseEntity<>("list not found", HttpStatus.NOT_FOUND);
	    serv.remove(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<Integer> createUsr(@RequestBody UsrDto dto) {
		if(dto.id<=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Usr usr = new Usr();
		usr.login = dto.login;
		usr.password = dto.password;
		usr.firstname = dto.firstname;
		usr.lastname = dto.lastname;
		// list.owner = UsrService.getById(idOwner); //TODO Fiddle with owner
		int id = serv.create(usr);
	    return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
	}

}
