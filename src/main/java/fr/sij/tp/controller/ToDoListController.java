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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fr.sij.tp.dto.TaskDto;
import fr.sij.tp.dto.TodoListDto;
import fr.sij.tp.entity.Task;
import fr.sij.tp.entity.TodoList;
import fr.sij.tp.service.TaskService;
import fr.sij.tp.service.TodoListService;
import fr.sij.tp.service.UsrService;

@RestController
@RequestMapping("todolists")
public class ToDoListController {
	
	@Autowired TodoListService serv;
	@Autowired UsrService usrService;
	@Autowired TaskService taskService;
	

	@GetMapping("")
	public List<TodoListDto> getAll() {
		List<TodoList> listAll = serv.getAll();
		List<TodoListDto> displayList = new ArrayList<TodoListDto>();
		
		for (TodoList list:listAll) {
			TodoListDto dto = new TodoListDto(list);
			displayList.add(dto);
		}
		
		return displayList;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoListDto> getById(@PathVariable int id) {
		TodoList list = serv.getById(id);
		if (list==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new TodoListDto(list), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<TaskDto>> getTasks(@PathVariable int id) {
		TodoList list = serv.getById(id);
		if (list==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new TodoListDto(list).taskIds, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createTodoList(@RequestBody TodoListDto dto) {
		if(dto.id<=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		TodoList list = new TodoList();
		list.color = dto.color;
		list.dueDate = dto.dueDate;
		list.owner = usrService.getById(dto.idOwner); //TODO Fiddle with owner
		int id = serv.create(list);
	    return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
	}
	
	@PostMapping("/{idList}/tasks")
	public void addTasks(@RequestBody ArrayNode taskArray, @PathVariable int idList) {
		
		TodoList list = serv.getById(idList);
		
		for(JsonNode taskNode:taskArray) {
			System.out.println("TAAAAAAAASK");
			Task task = new Task();
			
			if(task.content != null) {
				task.content = taskNode.get("content").asText();
				task.status = taskNode.get("status").asText();
				task.list = list;
				list.addTask(task); // NB Dans certaines versions de Spring besoin de coder la sauvegarde de la TodoList
				taskService.create(task);
			}
		}
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteList(@PathVariable int id) {
		TodoList list = serv.getById(id);
		if (list==null) return new ResponseEntity<>("list not found", HttpStatus.NOT_FOUND);
	    serv.remove(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/{idList}/tasks/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable int idList, @PathVariable int id) {
		TodoList list = serv.getById(idList);
		if (list==null) return new ResponseEntity<>("list not found", HttpStatus.NOT_FOUND);
		Task task = taskService.getById(id);
	    list.removeTask(task);
	    taskService.remove(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}