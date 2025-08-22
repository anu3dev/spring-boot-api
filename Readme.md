# Polling App

A simple Spring Boot application to manage polls.  
Supports basic operations:
- Display all polls as per user eligibility
- Mark/vote on a poll
- Delete a poll

---

## Project Structure

```
spring-boot-api/
├── pom.xml
├── src
│   ├── main
│   │   ├── java/com/learning/anu3dev
│   │   │   ├── Application.java         # Spring Boot main class
│   │   │   ├── controller               # REST controllers
│   │   │   │   └── PollController.java
│   │   │   ├── service                  # Business logic
│   │   │   │   └── PollService.java
│   │   │   ├── repository               # Data access
│   │   │   │   └── PollRepository.java
│   │   │   └── model                    # Entities/DTOs
│   │   │       └── Poll.java
│   │   └── resources
│   │       ├── application.properties  # Config (DB, ports)
│   │       └── data.sql                # Seed data (optional)
│   └── test/java/com/learning/anu3dev
│       └── PollControllerTest.java
└── README.md
```

---

## API list

|Method|Path|Description|
|-----------|----------------|--------------------------------|
|GET|`/`|Just displays welcome message|

---

## Learning

- Dependencies-
	- `spring-boot-starter-web` -> to build web applications and RESTful APIs
	- It includes
		- Spring MVC –> for building web applications and REST APIs
		- Jackson –> for JSON serialization/deserialization
		- Tomcat (embedded) –> as the default web server
		- Validation API –> for request validation
		- Logging –> via SLF4J and Logback
		
	- `spring-boot-starter-test` -> used for unit testing, integration testing, and mocking
	- It includes
		- JUnit 5 -> Core testing framework
		- Mockito -> Mocking dependencies
		- Spring Test	-> Integration with Spring context
		- AssertJ -> Fluent assertions
		- Hamcrest -> Matcher-based assertions
		- JsonPath -> JSON content verification
		- Spring Boot Test -> Utilities for Spring Boot-specific testing
	
	- `spring-boot-devtools` -> enabling features like automatic restarts, live reload, and better debugging support
	- It includes
		-  Automatic Restart -> Restarts the application when classpath files change (e.g., after saving a Java file).
		- LiveReload -> Automatically refreshes the browser when static resources (HTML, CSS, JS) change.
		- Enhanced Debugging -> Disables caching and enables detailed error pages for easier debugging.
		Remote Debugging -> Allows remote devtools connection for cloud-hosted apps (disabled by default).
	
	- `spring-boot-starter-data-jpa` ->  simplify and streamline working with databases using JPA
	- It includes
		- Spring Data JPA –> abstraction over JPA
		- Hibernate –> default JPA implementation
		- Jakarta Persistence API –> for annotations like @Entity, @Table, etc.
		- Transaction management –> via Spring
		- Auto-configuration –> Spring Boot sets up the EntityManager, DataSource, etc.
		- Connect to a database
		- Define entity classes using JPA annotations (@Entity, @Id, etc.)
		- Use Spring Data repositories (CrudRepository, JpaRepository)
		- Automatically configure Hibernate (the default JPA provider)
		- Handle transactions and data access with minimal boilerplate
	
	- `lombok` -> educe boilerplate code in your classes, especially for POJOs (Plain Old Java Objects) like entities, DTOs, and models.
		- It includes
			- Automatically generates: `@Getter / @Setter`, `@ToString`, `@EqualsAndHashCode`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor`, @Builder for fluent object creation, @Data for a combination of common annotations
		- Reduces boilerplate code in model/entity classes
		- Improves readability and maintainability
		- Speeds up development without sacrificing functionality
		
- 