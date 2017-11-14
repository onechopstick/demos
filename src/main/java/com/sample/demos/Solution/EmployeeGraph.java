package com.sample.demos.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.sample.demos.Solution.model.Employee;

/**
 * Goal of this class is to allow users to create an Employee Graph
 * in the Neo4j Database. The intention of this package is to allow
 * add/update, delete and list operations via command line.
 * 
 * Pre-Requisite: Running Neo4j Database on default port 7687
 * 
 * @author aji.janis
 *
 */
public class EmployeeGraph
{
	private Driver driver;
	private static final String BOTL_URI = "bolt://localhost:7687";
	private static final String USERNAME = "neo4j";
	private static final String PSWD = "neo4j";

	//default constructor
	public EmployeeGraph()
	{
        driver = GraphDatabase.driver(BOTL_URI, AuthTokens.basic(USERNAME, PSWD));
	}
	
	//constructor that can be used to pass non-default values
	public EmployeeGraph(String uri, String username, String pswd)
	{
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username, pswd));
	}
	
	/**
	 * Add Employee to DB. If employee already exists, the values
	 * will be updated.
	 * @param empName
	 * @param empId
	 */
	public void addEmployee(String empName, int empId)
	{
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("name", empName);
    	parameters.put("id", empId);
        try (Session session = driver.session())
        {
            try (Transaction tx = session.beginTransaction())
            {
                tx.run("MERGE (a:Employee { name: {name}, id: {id} })", parameters);
                tx.success();  
            }
        }
	}
	
	/**
	 * Delete or Reset the graph. Note that this will clear out
	 * all existing values in the graph.
	 */
	public void deleteGraph()
	{
    	try (Session session = driver.session())
        {
            StatementResult result = session.run(
                    "MATCH (a) DETACH DELETE a" );
            while (result.hasNext())
            {
            	System.out.println("Record STILL EXISTS! Something is wrong!");
                Record record = result.next();
                System.out.println(record.get("name").asString());
            }
        }
    	System.out.println("Graph Deleted.");
	}
	
	/**
	 * Get a list of Employee objects in the DB. 
	 * List size is 0 if DB is empty
	 * @return
	 */
	public List<Employee> getEmployees()
	{
		List<Employee> empList = new ArrayList<Employee>();
		
        try (Session session = driver.session())
        {
            StatementResult result = session.run(
                    "MATCH (a:Employee)  RETURN a.name AS name, a.id AS id" );
 
        	while (result.hasNext())
	        {
	            Record record = result.next();
	            empList.add(new Employee( 
	            			record.get("name").asString(), 
	            			record.get("id").asInt())
	            		);
	        }
        }
		return empList;
	}
	
	/**
	 * Print all (if any) Employees listed in DB.
	 */
	public void printEmployees()
	{
		System.out.println("Current Employees in the graph : \n\n");
		for (Employee e: getEmployees())
		{
			System.out.println(e.toString());
		}
	}
	/**
	 * Main method acts as the main driver for this simple program.
	 * In a proper enterprise architecture the functions will be called
	 * by another client.
	 * @param args
	 */
	public static void main (String [] args)
	{
		//warning to show user when invalid args are passed
		String warning = "INVALID ARGUMENTS! Sample commands are - "
				+ "1) mvn exec:java "
				+ "-Dexec.mainClass=\"com.sample.demos.Solution.EmployeeGraph\" "
				+ "-Dexec.args=\"add aji 1234\""
				+ "\nOR\n"
				+ "2) mvn exec:java "
				+ "-Dexec.mainClass=\"com.sample.demos.Solution.EmployeeGraph\" "
				+ "-Dexec.args=\"print\"";
		
		//user must pass atleast one valid arg
		if (args == null || args.length == 0)
		{
			System.out.println(warning);
		}
		//add Employee 
		else if (args[0].equalsIgnoreCase("add"))
		{
			if (args.length != 3)
			{
				System.out.print(warning);
			}
			else
			{
				System.out.println("Creating employee with name: "+args[1]
					+ ", and ID: " + args[2]
					);
				EmployeeGraph emp = new EmployeeGraph();
				int empId = Integer.parseInt(args[2]);
				emp.addEmployee(args[1], empId);
				System.out.println("DONE");
			}
		}
		//print all Employees to screen
		else if (args[0].equalsIgnoreCase("print"))
		{
			EmployeeGraph emp = new EmployeeGraph();
			emp.printEmployees();
		}
		//delete Employee Graph
		else if (args[0].equalsIgnoreCase("delete"))
		{
			EmployeeGraph emp = new EmployeeGraph();
			emp.deleteGraph();
		}
		//invalid args, show warning
		else System.out.println( warning);
		
		//Friendly message to indicate end of execution
		System.out.println("\n\nExiting\n\n");
	}
}
