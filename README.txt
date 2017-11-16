##########################################################
README for websolution webapp project
##########################################################

PROJECT OBJECTIVE
The goal of this project is to utilize Neo4j Graph DB to create a
database of Employees. This is meant to be a demo project that does 
the following -

1) Parse name and id values from a HTML form and add an Employee Graph node
2) Display on the UI a list of Employee nodes to user when requested

Please read the directions below on how to setup and execute this demo.

------------------------
Pre-requisite:
------------------------
1) Apache Tomcat v 9.0
2) Running Neo4j DB on default port 7687
3) Java 1.8
4) Source code to build or websolution-1.war from github
5) Maven 3.5.0 or later if building from source code


------------------------------------------------------------
Running from command line without building source code
------------------------------------------------------------

1. Copy websolution-1.war to your TOMCAT_DIR/webapps/ directory
2. Start Tomcat instance TOMCAT_DIR/bin/startup.sh (or startup.bat on windows)
3. Navigate to the below url in your browser to see current graph nodes 
	http://localhost:8080/websolution-1/employees
	
4. Navigate to the below url in your browser to add new graph nodes
	http://localhost:8080/websolution-1/addemployees
	 
	(Note that after ADD, it will take you back to the employees list page)
	


------------------------------------------------
Building source code and executing with Maven 
------------------------------------------------

1. Install, Create and Start a DB in Neo4j
2. Download source code and cd into directory
3. Execute: mvn clean install
   You should now have a websoluiton-1.war under target directory
   
4. Copy websolution-1.war to your TOMCAT_DIR/webapps/ directory
5. Start Tomcat instance TOMCAT_DIR/bin/startup.sh (or startup.bat on windows)
6. Navigate to the below url in your browser to see current graph nodes 
	http://localhost:8080/websolution-1/employees
	
7. Navigate to the below url in your browser to add new graph nodes
	http://localhost:8080/websolution-1/addemployees
	 
	(Note that after ADD, it will take you back to the employees list page)
	
