##########################################################
README for simplesolution Java project
##########################################################

PROJECT OBJECTIVE
The goal of this project is to utilize Neo4j Graph DB to create a
database of Employees. This is meant to be a demo project that does 
the following -

1) Parse name and id values from command line and add an Employee Graph node
2) Print a list of Employee nodes to user when requested

Please read the directions below on how to setup and execute this demo.

------------------------
Pre-requisite:
------------------------
1) Java 1.8
2) Running Neo4j DB on default port 7687 (version 3.3.0)
3) Source code to build or simplesolution-1.jar from github
4) Maven 3.5.0 or later if building from source code

------------------------------------------------------------
Running from command line without building source code
------------------------------------------------------------

1. Install, Create and Start a DB in Neo4j
2. Download simplesolution-1.jar 
2. Command to print graph >>
		java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph print
3. Command to add new nodes to graph >>
        java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph add bob 1234		
		java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph add "bob smith" 1234



------------------------------------------------
Building source code and executing with Maven 
------------------------------------------------

1. Install, Create and Start a DB in Neo4j
2. Download source code and cd into directory
3. Execute: mvn clean install
   You should now have a simplesolution-1.jar under target directory

4. Command to print graph >>
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="print"
5. Command to add new nodes to graph >>
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="add bob 1234"
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="add \"bob smith\" 1234"

