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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.sij.tp.dto.TaskDto;
import fr.sij.tp.dto.TodoListDto;
import fr.sij.tp.dto.UsrDto;
import fr.sij.tp.entity.Task;
import fr.sij.tp.entity.TodoList;
import fr.sij.tp.entity.Usr;
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
	
//	@PostMapping
//	public ResponseEntity<Integer> createTodoList(@RequestBody TodoList list) {
//		if(list.id<=0) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		int id = serv.create(list);
//	    return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
//	}
	
	@PostMapping
	public ResponseEntity<Integer> createTodoList(@RequestBody TodoListDto dto) {
		if(dto.id<=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		TodoList list = new TodoList();
		list.color = dto.color;
		list.dueDate = dto.dueDate;
		list.title = dto.title;
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
				list.addTask(task);
				taskService.create(task);
			}
		}
		
	}
	
//	@PostMapping(value = "/{idList}/tasks", consumes = "application/json", produces = "application/json")
//	public void addTask(@RequestBody TaskDto newTask, @PathVariable int idList) throws Exception {
//		TodoListDto list = repo.get(idList);
//		if (list==null) throw new Exception("List not existing");
//		
//		int indexTask = list.getIndex(newTask.id);
//		if (indexTask>=0) throw new Exception("Task already existing");
//		
//		newTask.parentList = list;
//	    list.tasks.add(newTask);
//	}
	
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


//	// localhost:8080/todolists
//	@GetMapping("/full")
//	public List<TodoListDto> getAll() {
//		return repo.getAll();
//	}
//	
//	@GetMapping("")
//	public ArrayNode getAllShort() {
//		List<TodoListDto> bigList = repo.getAll();
//		ObjectMapper mapper = new ObjectMapper();
//		ArrayNode shortList = mapper.createArrayNode();
//		
//		for (TodoListDto list:bigList) {
//			shortList.add(list.toShortJsonNode());
//		}
//		return shortList;
//	}
//	
//	@GetMapping("/{idList}/tasks")
//	public ResponseEntity<List<TaskDto>> getTasks(@PathVariable int idList)  {
//		TodoListDto list = repo.get(idList);
//		if(list==null) return ResponseEntity.notFound().build();
//		return ResponseEntity.ok().body(list.tasks); // Exemple de l'autre syntaxe des ResponseEntity
//	}
//	
//	@GetMapping("/testdate")
//	public Date displayDate(@RequestParam("date") String date) throws ParseException {
//		
//		String pattern = "yyyyMMdd";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//		
//		return simpleDateFormat.parse(date);
//	}
//	
//	@PostMapping(value = "/{idList}/tasks", consumes = "application/json", produces = "application/json")
//	public void addTask(@RequestBody TaskDto newTask, @PathVariable int idList) throws Exception {
//		TodoListDto list = repo.get(idList);
//		if (list==null) throw new Exception("List not existing");
//		
//		int indexTask = list.getIndex(newTask.id);
//		if (indexTask>=0) throw new Exception("Task already existing");
//		
//		newTask.parentList = list;
//	    list.tasks.add(newTask);
//	}
//	
//	@PutMapping(value = "/full/{id}", consumes = "application/json", produces = "application/json")
//	public void update(@RequestBody TodoListDto newList, @PathVariable int id) {
//		newList.id = id;
//	    repo.add(newList);
//	}
//	
//	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<String> updateJson(@RequestBody String updateStr, @PathVariable int id) {
//		ObjectMapper mapper = new ObjectMapper();
//		TodoListDto oldList = repo.get(id);
//		
//		try {
//			JsonNode newNode = mapper.readTree(updateStr);
//			oldList.color = newNode.get("color").asText();
//			oldList.title = newNode.get("title").asText();
//			oldList.owner = newNode.get("owner").asText();
//			oldList.dueDate = new Date(newNode.get("dueDate").asLong());
//			
//			
//			
//			return new ResponseEntity<String>(updateStr, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().build();
//		}
//	}
//	
//	@PutMapping(value = "/{idList}/tasks/{idTask}", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto newTask, @PathVariable int idList, @PathVariable int idTask) {
//		TodoListDto list = repo.get(idList);
//		if (list==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		
//		int indexTask = list.getIndex(idTask);
//		if (indexTask<0) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		
//		TaskDto oldTask = list.tasks.get(indexTask);
//		oldTask.status = newTask.status;
//		oldTask.content = newTask.content;
//		return new ResponseEntity<TaskDto>(oldTask,HttpStatus.NO_CONTENT);
//	}
//	
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<String> deleteList(@PathVariable int id) {
//		TodoListDto list = repo.get(id);
//		if (list==null) return new ResponseEntity<>("list not found", HttpStatus.NOT_FOUND);
//	    repo.remove(id);
//	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//	
//
//	@DeleteMapping("/{idList}/tasks/{idTask}")
//	public ResponseEntity<String> deleteTask(@PathVariable int idList,  @PathVariable int idTask) {
//		TodoListDto list = repo.get(idList);
//		if (list==null) return new ResponseEntity<>("list not found", HttpStatus.NOT_FOUND);
//		
//		int indexTask = list.getIndex(idTask);
//		if (indexTask<0) return new ResponseEntity<>("Task not existing", HttpStatus.NOT_FOUND);
//		
//		list.tasks.remove(indexTask);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//	
//	@GetMapping("/jackson")
//	public ResponseEntity<String> getObject() {
//		ObjectMapper mapper = new ObjectMapper();
//		
//		String jsonIn ="{ \"age\" : 12, \"nom\" : \"Patrick\"}";
//		try {
//			JsonNode node = mapper.readTree(jsonIn);
//			node.isArray();
//			node.isObject();
//			int age = node.get("age").asInt();
//			String nom = node.get("nom").asText();
//			
//			String result = mapper.writeValueAsString(new String("toto"));
//			return new ResponseEntity<String>(result, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().build();
//		}
//	}
//	
//	@GetMapping("/jackson/{id}")
//	public ResponseEntity<String> verifJsonList(@PathVariable int id) {
//		ObjectMapper mapper = new ObjectMapper();
//		
//		ObjectNode listNode = repo.get(id).toJsonNode();
//		
//		try {
//			String result = mapper.writeValueAsString(listNode);
//			return new ResponseEntity<String>(result, HttpStatus.OK);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().build();
//		}
//	}
//	
}

