# Polling App

A simple Spring Boot application to manage polls.  
Supports basic operations:
- Display all polls as per user eligibility
- Mark/vote on a poll
- Delete a poll

---

## Project Structure

spring-boot-api/ ├── pom.xml ├── src │ ├── main │ │ ├── java/com/learning/anu3dev │ │ │ ├── Application.java # Spring Boot main class │ │ │ ├── controller # REST controllers │ │ │ │ └── PollController.java │ │ │ ├── service # Business logic │ │ │ │ └── PollService.java │ │ │ ├── repository # Data access │ │ │ │ └── PollRepository.java │ │ │ └── model # Entities/DTOs │ │ │ └── Poll.java │ │ └── resources │ │ ├── application.properties # Config (DB, ports) │ │ └── data.sql # Seed data (optional) ├── test/java/com/learning/anu3dev │ └── PollControllerTest.java └── README.md

---

## API list

|Method|Path|Description|
|--------|----------|---------------------|
|GET|`/`|Just displays welcome message|

---

## Learning

- 