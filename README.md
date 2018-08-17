# mytaxi backend applicant test

## Task Description
You should be able to start the example application by executing com.mytaxi.MytaxiServerApplicantTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven
* Intellij as IDE is preferred but not mandatory. We do provide code formatter for intellij and eclipse in the etc folder.


You should be aware of the following conventions while you are working on this exercise:

 * All new entities should have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
 	* DataTransferObjects: Objects which are used for outside communication via the API
   * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
   * Service: Implements the business logic and handles the access to the DataAccessObjects.
   * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment is a good choice, but it's up to you how you are testing your code.

You should commit into your local git repository and include the commit history into the final result.

---


## Task 1
 * Write a new Controller for maintaining cars (CRUD).
 * Decide on your own how the methods should look like.
 * Entity Car: Should have at least the following characteristics: license_plate, seat_count, convertible, rating, engine_type (electric, gas, ...)
 * Entity Manufacturer: Decide on your own if you will use a new table or just a string column in the car table.
 * Extend the DriverController to enable drivers to select a car they are driving with.
 * Extend the DriverController to enable drivers to deselect a car.
 * Extend the DriverDo to map the selected car to the driver.
 * Add example data to resources/data.sql

---


## Task 2
First come first serve: A car can be selected by exactly one ONLINE Driver. If a second driver tries to select a already used car you should throw a CarAlreadyInUseException.

---


## Task 3
Imagine a driver management frontend that is used internally by mytaxi employees to create and edit driver related data. For a new search functionality, we need an endpoint to search for drivers. It should be possible to search for drivers by their attributes (username, online_status) as well as car characteristics (license plate, rating, etc).

* implement a new endpoint for searching or extend an existing one
* driver/car attributes as input parameters
* return list of drivers

---


## Task 4 (optional)
This task is _voluntarily_, if you can't get enough of hacking tech challenges, implement security.
Secure the API so that authentication is needed to access it. The details are up to you.

---


Good luck!
❤️ mytaxi



_NOTE: Please make sure to not submit any personal data with your tests result. Personal data is for example your name, your birth date, email address etc.





##Task 1. According to the first task, a new controller for maintaining cars has been written. It implements basic operations (CRUD) 

1.1 Firstly the car entity was created. Then the new characteristics were added to it, such as: license_plate, seat_count, convertible, rating, engine_type, carrying_capacity, manufacturer and availability. 
1.2 For a more practical usage was decided to use a new manufacturer table. It can provide further extension of the manufacturer entity. 
1.3 The DriverController has been extended to enable drivers to select a car they are driving with. (Method: selectCar in DriverController)
1.4 The DriverController has been extended to enable drivers to deselect a car. (Method: deSelectCar in DriverController)

## Task 2
According to the second task a check was made to monitor, if a second driver tried to select an already used car, it would be thrown a CarAlreadyInUseException. Also some exceptions have been added: CarAlreadyInUseException; DriverOfflineException.   

## Task 3
In order to enable a search for drivers by their attributes as well as car characteristics endpoints have been created ( see: findAllCarsByFilters, findAllCarsByAvailability in CarController, findAllDriversByCarFilters in DriverController). 
In these controllers is expected the input data in JSON format: then we build SQL query from JSON string, based on its properties (Jackson library was used)
