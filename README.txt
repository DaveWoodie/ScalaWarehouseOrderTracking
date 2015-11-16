To run the program please launch the window class.  The program will start but no further functionality will happen unless 
a database is set up with the structure contained within the mvc file included in the git repository. 

It also requires data to login and update anything. 

Many of the tests will also fail if there is no database connection.

If the Scala tests will not run in eclipse please enter the run configurations option and delete all current versions of the ScalaTest
run configurations, you should then be able to run the tests.

There are no tests for any of the items in graphics.componenets as the only relevant test that could be run is a check on the return type
of the method with are all graphics UNIT objects.

As of the last commit you may forward engineer the database with all it's data using SQL Workbench v 6.3 CE.  You should then be able to run the whole program.

Login is;
Username, Al
Password, 1
