#Work In Progress...

# t1-java
Java SDK for MediaMath Platform APIs

#build project
- For developer build
	>mvn clean install -Pdev

- For Production build
	>mvn clean install -Pprod

# Usage

- make sure the build path entries in your project are pointing to the jar file created.

### authentication

- using constructor

    	TerminalOne one = new TerminalOne(username, password, key);
  
- using authenticate method
	
		TerminalOne one = new TerminalOne();
	 	one.authenticate(username, password, key) 

### How to Post
	 
-	you need to import the Entities from the package `com.mediamath.terminalone.models.*;`

	######Example
		
		TerminalOne one = new TerminalOne();
		one.authenticate("someUser", "somepwd", "somekey");

		Agency agency = new Agency();
		agency.setName("testAgency");
		agency.setOrganization_id(10001);

		agency = one.save(agency);

	The above snippet of code sets the agency entity attributes and saves it with the save method of TerminalOne

	The save method is overloaded accepting different Entities as parameters, Entities are derived from T1Entity which serves as a base `interface` for all the entities.
	
	The save method throws `ClientException` or `ParseException` in case of any error occurs.

	The return type of the save method can be the same Type of Entity as passed into the argument OR it can be a `JsonResponse<? extends T1Entity>`  

