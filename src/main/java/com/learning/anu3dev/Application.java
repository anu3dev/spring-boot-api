package com.learning.anu3dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

/**
 * 
 * Integer is 4 byte and Long is 8 byte.
 * there is no fix size of string.
 * 
 * Spring automatically changes camelCase names into 
 * snake_case for database tables and columns.
 */