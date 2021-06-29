package sg.edu.iss.cats.controllers;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.iss.cats.model.Employee;
import sg.edu.iss.cats.model.User;


public class UserSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = null;
	private Employee employee = null;
	private ArrayList<Employee> subordinates = null;
	
	public UserSession() {
		super();
	}
   //String sessionId,
	public UserSession( User user, Employee employee, ArrayList<Employee> subordinates) {
		super();
		//this.sessionId = sessionId;
		this.user = user;
		this.employee = employee;
		this.subordinates = subordinates;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(ArrayList<Employee> subordinates) {
		this.subordinates = subordinates;
	}



}
