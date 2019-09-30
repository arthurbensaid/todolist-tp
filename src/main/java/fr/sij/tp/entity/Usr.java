package fr.sij.tp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Usr extends GenericEntity {
	
	public String login;
	public String password;
	public String firstname;
	public String lastname;
	
	@OneToMany(mappedBy="owner") // Attention dans le mappedBy on précise le nom de l'attribut de la classe dont part le ManyToOne !!
	public List<TodoList> todolists = new ArrayList<>();

}