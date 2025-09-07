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
│   │   │   │		└── PollController.java
│   │   │   ├── service                  # Business logic
│   │   │   │		└── PollService.java
│   │   │   ├── repository               # Data access
│   │   │   │		└── PollRepository.java
│   │   │   ├── model                    # Entities/DTOs
│   │   │   │     └── Poll.java
│   │   │   ├── dto					 # Request/Response DTOs (API pay-loads)
│   │   │   │		└── CreatePolls.java
│   │   │   ├── security				 # (optional) Security config, filters
│   │   │   │		└── SecurityConfig.java
│   │   │   ├── advice					 # Global exception handler
│   │   │   │		└── GlobalExceptionHandler.java  
│   │   └── resources
│   │       ├── application.properties   # Config (DB, ports)
│   │       └── data.sql                 # Seed data (optional)
│   └── test/java/com/learning/anu3dev
│       └── PollControllerTest.java
└── README.md
```

---

## Poll API Documentation - API list

---

### 1. Add initial poll

**Endpoint:** `/poll/add-polls`

**Type:** `post`

**Description:** pollId is missing means it will create new poll, isActive is missing means poll won't be activated

 
**Payload:**

<details>
  <summary>Click to expand</summary>

```json
[
    {
        "isActive": "true",
        "pollTitle": "About programming",
        "pollId" : "",
        "questions": [
            {
                "questionTitle": "Which programming language do you like the most?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "Java",
                    "Python",
                    "JavaScript",
                    "Go",
                    "C#"
                    ]
            },
            {
                "questionTitle": "Which IDE do you like the most?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "VSCode",
                    "IntelliJ",
                    "Eclipse",
                    "Notepad++"
                    ]
            },
            {
                "questionTitle": "Which courses you want?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Java",
                    "React",
                    "TypeScript",
                    ".Net"
                    ]
            }
        ]
    },
    {
        "isActive": "",
        "pollTitle": "About yourself",
        "pollId" : "",
        "questions": [
            {
                "questionTitle": "Which city you belong?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "Patna",
                    "Pune",
                    "Noida",
                    "Chennai"
                    ]
            },
            {
                "questionTitle": "Which location you can join?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Mumbai",
                    "Ranchi",
                    "Kolkata",
                    "Bhuvneshwar"
                    ]
            }
        ]
    }
]
```

</details>

**Response:**

<details>
  <summary>Click to expand</summary>

```json
{
  "page": 1,
}
```

</details>

---

### 2. Add questions to existing poll

**Endpoint:** `/poll/add-polls`

**Type:** `post`

**Description:** pollId is present then will add questions against mentioned poll, if pollId is present and question is new then only questions will be added else it will be ignored, questions with null title value will be ignored

 
**Payload:**

<details>
  <summary>Click to expand</summary>

```json
[
    {
        "isActive": "true",
        "pollTitle": "About programming",
        "pollId" : 1,
        "questions": [
            {
                "questionTitle": "Which programming language do you like the most?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "Java",
                    "Python",
                    "JavaScript",
                    "Go",
                    "C#"
                    ]
            },
            {
                "questionTitle": "Which IDE do you like the most?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "VSCode",
                    "IntelliJ",
                    "Eclipse",
                    "Notepad++"
                    ]
            },
            {
                "questionTitle": "Which courses you want?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Java",
                    "React",
                    "TypeScript",
                    ".Net"
                    ]
            },
            {
                "questionTitle": "Your total experience?",
                "isActive": false,
                "isMultiSelect": false,
                "options": [
                    "0 - 2",
                    "2 - 5",
                    "5 - 10",
                    "More than 10"
                    ]
            }
        ]
    },
    {
        "isActive": "true",
        "pollTitle": "About yourself",
        "pollId" : 2,
        "questions": [
            {
                "questionTitle": "Which city you belong?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "Patna",
                    "Pune",
                    "Noida",
                    "Chennai"
                    ]
            },
            {
                "questionTitle": "Which location you can join?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Mumbai",
                    "Ranchi",
                    "Kolkata",
                    "Bhuvneshwar"
                    ]
            }
        ]
    }
]
```

</details>

**Response:**

<details>
  <summary>Click to expand</summary>

```json
{
  "page": 2,
}
```

</details>

---

### 3. Add new poll

**Endpoint:** `/poll/add-polls`

**Type:** `post`

**Description:** Just add a poll without pollId, it will create new poll

 
**Payload:**

<details>
  <summary>Click to expand</summary>

```json
[
    {
        "isActive": "",
        "pollTitle": "About yourself",
        "pollId" : 2,
        "questions": [
            {
                "questionTitle": "Which city you belong?",
                "isActive": true,
                "isMultiSelect": false,
                "options": [
                    "Patna",
                    "Pune",
                    "Noida",
                    "Chennai"
                    ]
            },
            {
                "questionTitle": "Which location you can join?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Mumbai",
                    "Ranchi",
                    "Kolkata",
                    "Bhuvneshwar"
                    ]
            }
        ]
    },
    {
        "isActive": "true",
        "pollTitle": "Your food",
        "pollId" : "",
        "questions": [
            {
                "questionTitle": "Which food you like most?",
                "isActive": true,
                "isMultiSelect": true,
                "options": [
                    "Pizza",
                    "Burger",
                    "Paani puri",
                    "Chaat"
                    ]
            }
        ]
    }
]
```

</details>

**Response:**

<details>
  <summary>Click to expand</summary>

```json
{
  "page": 3,
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