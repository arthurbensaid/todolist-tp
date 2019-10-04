package fr.sij.tp.dto;

import java.util.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.sij.tp.entity.TodoList;
import fr.sij.tp.entity.Usr;


public class UsrDto {
	
	public int id;
	@NotNull public String login;
	@NotNull public String password;
	@NotNull public String firstname;
	@NotNull public String lastname;
	public List<TodoListDto> todolists = new ArrayList<>();
	
	public UsrDto() {
		super();
	}
	
	public UsrDto(Usr usr) {
		super();
		
		this.id = usr.id;
		this.login = usr.login;
		this.password = usr.password;
		this.firstname = usr.firstname;
		this.lastname = usr.lastname;
		for(TodoList list:usr.todolists)this.todolists.add(new TodoListDto(list));
	}
	
//	public ObjectNode toJsonNode() {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode usrNode = mapper.createObjectNode();
//		ArrayNode listArrayNode = mapper.createArrayNode();
//		
//		usrNode.put("id", this.id);
//		usrNode.put("login", this.login);
//		usrNode.put("password", this.password);
//		usrNode.put("firstname", this.firstname);
//		usrNode.put("lastname", this.lastname);
//		usrNode.putArray("lists");
//		
//		for (TodoListDto list:this.todolists) {
//			ObjectNode taskJson = list.toJsonNode();
//			listArrayNode.add(taskJson);
//		}
//		usrNode.set("lists", listArrayNode);
//		
//		return usrNode;
//	}

}