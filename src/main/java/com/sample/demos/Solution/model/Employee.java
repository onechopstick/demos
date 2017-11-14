package com.sample.demos.Solution.model;
/**
 * Employee Object 
 * @author aji.janis
 *
 */
public class Employee 
{
	private String name; // Employee Full Name
	private int id; //Employee ID
	
	//constructor forcing user to pass name and id
	public Employee(String eName, int eId)
	{
		this.name = eName;
		this.id = eId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [name=" + name.toUpperCase() + ", id=" + id + "]\n";
	}
}
