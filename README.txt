##########################################################
README for websolution webapp project
##########################################################

Pre-requisite:
1) Apache Tomcat v 9.0
2) Running Neo4j DB on default port 7687


-----------------------------------------
Building Webapp (optional)
-----------------------------------------

Pre-req: Requires Maven, Java 8 and Tomcat 9

1. Create and Start a DB in Neo4j
2. Download source code and cd into directory
3. Execute: mvn clean install

You should now have a websoluiton-1.war under target directory

-----------------------------------------
Running Webapp via Tomcat
-----------------------------------------

1) Copy websolution-1.war to your TOMCAT_DIR/webapps/ directory
2) Start Tomcat instance TOMCAT_DIR/bin/startup.sh (or .bat)
3) Navigate to this url in your browser to see current graph nodes 
	http://localhost:8080/websolution-1/employees
	
4) Navigate to this url in your browser to add new employee nodes
	http://localhost:8080/websolution-1/addemployees
	 
	(Note that after ADD, it will take you back to the list page)
	
