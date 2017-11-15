package com.sample.demos.Solution;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;


//Import required java libraries
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Goal of this class is to allow users to create an Employee Graph
 * in the Neo4j Database. The intention of this package is to allow
 * add/update and list operations via browser.
 * 
 * Pre-Requisite: 
 * 1) Running Neo4j Database on default port 7687
 * 2) Running Apache Tomcat instance
 * 
 * @author aji.janis
 *
 */
public class EmployeeGraph extends HttpServlet
{
	private Driver driver;
	private static final String BOTL_URI = "bolt://localhost:7687";
	private static final String USERNAME = "neo4j";
	private static final String PSWD = "neo4j";

	private static final long serialVersionUID = -4751096228274971485L;

	/**
	 * init() called when app is first started up
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started ....");
	}
	/**
	 * Useful for cleaning up any resources. Not really applicable
	 * in this example
	 */
	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped.");
	}
	/**
	 * Called when GET() is invoked. 
	 * When client goes to /employees url
	 * this function will be called
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Current Employees in graph:\n");
		EmployeeGraph graph = new EmployeeGraph();
		response.getWriter().println(graph.getEmployees());
		
	}
	/**
	 * Called when POST() is invoked.
	 * When client clicks "Add Employee" from /addemployees url
	 * this function will be called
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empName = request.getParameter("emp_name");
        String id = request.getParameter("emp_id");
        int empId = Integer.parseInt(id);
        EmployeeGraph graph = new EmployeeGraph();
        graph.addEmployee(empName, empId);
        
        //redirect to show list of all Employees in graph
        doGet(request, response);
    }
	/**
	 * Default constructor
	 */
	public EmployeeGraph()
	{
        driver = GraphDatabase.driver(BOTL_URI, AuthTokens.basic(USERNAME, PSWD));
	}
	/**
	 * Custom constructor
	 * @param uri
	 * @param username
	 * @param pswd
	 */
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
	 * Get a list of Employee objects in the DB. 
	 * List size is 0 if DB is empty
	 * @return
	 */
	public String getEmployees()
	{
		String empData = "";
        try (Session session = driver.session())
        {
            StatementResult result = session.run(
                    "MATCH (a:Employee)  RETURN a.name AS name, a.id AS id" );
 
        	while (result.hasNext())
	        {
	            Record record = result.next();
	            empData = empData.concat("Employee Name: " + record.get("name").asString());
	            empData = empData.concat(", Employee ID: " + record.get("id").asInt());
	            empData = empData.concat("\n");
	        }
        }
		return empData;
	}
	/**
	 * Print all (if any) Employees listed in DB.
	 */
	public void printEmployees()
	{
		System.out.println("Current Employees in the graph : \n\n");
		System.out.println(getEmployees());
	}
}
