package fr.sij.tp.dto;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.sij.tp.entity.Task;
import fr.sij.tp.entity.TodoList;

public class TodoListDto {

	public int id;
	public String color;
	public String title;
	public int idOwner;
	public Date dueDate;
	public String username;
	public List<TaskDto> taskIds = new ArrayList<>();
	
	public TodoListDto() {
		super();
	}
	
	public TodoListDto(TodoList list) {
		super();
		
		this.id = list.id;
		this.color = list.color;
		this.title = list.title;
		this.idOwner = list.owner.id;
		this.username = list.owner.firstname + " " + list.owner.lastname;
		for(Task task : list.tasks)taskIds.add(new TaskDto(task));
	}
	
//	public ObjectNode toJsonNode() {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode listNode = mapper.createObjectNode();
//		ArrayNode taskArrayNode = mapper.createArrayNode();
//		
//		listNode.put("id", this.id);
//		listNode.put("color", this.color);
//		listNode.put("title", this.title);
//		listNode.put("idOwner", this.idOwner);
//		listNode.put("username", this.username);
//		listNode.put("dueDate", this.dueDate.getTime());
//		listNode.putArray("tasks");
//		
//		for (TaskDto task:this.taskIds) {
//			ObjectNode taskJson = task.toJsonNode();
//			taskArrayNode.add(taskJson);
//		}
//		listNode.set("tasks", taskArrayNode);
//		
//		return listNode;
//	}
}
