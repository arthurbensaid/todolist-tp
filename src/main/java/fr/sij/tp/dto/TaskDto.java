package fr.sij.tp.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.sij.tp.entity.Task;

public class TaskDto {
	
	public String content;
	public String status;
	public int id;
	

	public TaskDto() {
		super();
		
	}
	
	public TaskDto(Task t) {
		super();
		this.id = t.id;
		this.status = t.status;
		this.content = t.content;
	}
	
//	public ObjectNode toJsonNode() {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode shortNode = mapper.createObjectNode();
//		shortNode.put("id", this.id);
//		shortNode.put("color", this.status);
//		shortNode.put("title", this.content);
//		
//		return shortNode;
//		}

}