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
│   │   │   ├── model                    # Entities/DTOs
│   │   │   │     └── Poll.java
│   │   │   ├── dto					 #  Request/Response DTOs (API pay-loads)
│   │   │   │	   └── CreatePolls.java
│   │   │   ├── security				 # (optional) Security config, filters
│   │   │   │     └── SecurityConfig.java
│   │   └── resources
│   │       ├── application.properties  # Config (DB, ports)
│   │       └── data.sql                # Seed data (optional)
│   └── test/java/com/learning/anu3dev
│       └── PollControllerTest.java
└── README.md
```

---

## API list

|Method|Path|Description|Sample response|
|-----------|----------------|--------------------------------|----------|
|GET|`/`|Just displays welcome message|"Hello, World!"|
|GET|`/poll/get-polls`|displays all  polls with active status and polls will have questions with active status|<details><summary>Show Response</summary>

```json
{
  "pollId": 1,
  "pollTitle": "My Poll",
  "active": true,
  "questions": [
    {
      "question": "Your favorite color?",
      "multiSelect": false,
      "options": ["Red", "Blue", "Green"]
    }
  ]
}
```
</details>

---

## Learning

- Dependencies
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
		
	- `mysql-connector-j` -> official JDBC driver provided by MySQL to allow Java applications—including Spring Boot apps—to connect and interact with a MySQL database.
	
	- `postgresql` -> PostgreSQL JDBC driver as a runtime dependency
	
- Profile setup
	- to create multiple profiles like for dev, IT, UAT, STAGE, PROD etc.
	
- Annotation
	- `@SpringBootApplication`
		- starting point of my app. Set up everything I need automatically and find my other classes.
		- `@Configuration` –> Marks the class as a place where Spring can get instructions about how to create things (called beans).

		- `@EnableAutoConfiguration` –> Spring Boot will look at what’s on your classpath (the libraries your project has) and automatically set up some things for you.

		- `@ComponentScan` –> Spring looks in the package of this class (and subpackages) to find your controllers, services, repositories, etc.
	
	- `@RestController`
		- this class will handle HTTP requests and give responses, usually in JSON format.
		- `@Controller` -> Marks the class as a controller.
		- `@ResponseBody` -> Automatically converts return values to JSON (or other formats) instead of rendering HTML.
		
	- `@RequestMapping`
		- will handle requests to this URL (any type).
		- `@GetMapping` -> handle GET requests to this URL (read-only).
		- `@PostMapping` -> handle POST requests to this URL (send/submit data).
		- `@PutMapping` -> update
		- `@DeleteMapping` -> delete
		
	- `@Data`
		- Getters for all fields
		- Setters for all fields
		- toString() method
		- equals() and hashCode() methods
		- Required constructors (like no-args constructor)
		
	- `@Entity`
		- JPA (Java Persistence API) annotation used in Spring Boot to mark a class as a database table.
		- POJO is a simple Java class with fields, getters, setters, and maybe a constructor. When you want a POJO to be stored in a database, we use @Entity 
		- Works with Spring Data JPA to automatically handle saving, updating, deleting, and querying data.
		- `@Id` -> Marks the primary key.
		- `@GeneratedValue` -> Auto-generates ID values.
		- `@Table` -> Custom name for the table
		- `@Column` -> Custom name/settings for a column
		- Relationships:
			- `@OneToMany` -> 1 parent, many children
			- `@ManyToOne` -> many children, 1 parent
			- `@OneToOne` -> 1 to 1
			- `@ManyToMany` -> multiple links on both sides
			
	- 