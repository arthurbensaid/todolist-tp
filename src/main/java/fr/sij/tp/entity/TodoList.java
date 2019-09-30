package fr.sij.tp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="todolist")
public class TodoList extends GenericEntity{
	
	public String title;
	public String color;
	@Column(name="due_date")  public Date dueDate;
	
	@ManyToOne
	@JoinColumn(name="id_owner")
	public Usr owner;
	
	@OneToMany(mappedBy="list")
	public List<Task> tasks = new ArrayList<>();

	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public boolean removeTask(Task task) {
		return this.tasks.remove(task);
	}

}