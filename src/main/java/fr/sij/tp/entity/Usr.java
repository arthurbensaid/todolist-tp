package fr.sij.tp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@NamedQueries({
@NamedQuery(name="Usr.findByLowPassword", query="SELECT u FROM Usr u WHERE length(u.password) <8"),
@NamedQuery(name="Usr.countUsers", query="SELECT COUNT(u) FROM Usr u")
})

public class Usr extends GenericEntity {
	
	public String login;
	public String password;
	public String firstname;
	public String lastname;
	
	@JsonIgnore // TODO Remove this
	@OneToMany(mappedBy="owner") // Attention dans le mappedBy on précise le nom de l'attribut de la classe dont part le ManyToOne !!
	public List<TodoList> todolists = new ArrayList<>();

}