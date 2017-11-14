##########################################################
README for simplesolution java project
##########################################################

Pre-requisite:
1) Java 1.8
2) Running Neo4j DB on default port 7687
3) (Optional) Maven

-----------------------------------------
Running from command line without Maven
-----------------------------------------

1. Create and Start a DB in Neo4j
2. Command to print graph >>
		java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph print
3. Command to add new nodes to graph >>
        java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph add bob 1234		
		java -cp <jar_location>/simplesolution-1.jar com.sample.demos.Solution.EmployeeGraph add "bob smith" 1234
		
-----------------------------------------
Running from command line with Maven
-----------------------------------------

1. Create and Start a DB in Neo4j
2. Download source code and cd into directory
3. Execute: mvn clean install
4. Command to print graph >>
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="print"
5. Command to add new nodes to graph >>
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="add jane 90"
		mvn exec:java -Dexec.mainClass="com.sample.demos.Solution.EmployeeGraph" -Dexec.args="add \"Jane Smith\" 99"

