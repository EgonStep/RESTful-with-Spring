package com.rest.webservices.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * REST is Representational State Transfer
 *  - Is a style of software architecture for distributed hypermedia systems
 *  - Make best use of HTTP
 *
 * Key Abstraction - Resource
 *  - A resource has an URI (Uniform Resource Identifier) Ex. /users/Ranga/todos/1
 *  - A resource can have different representations. Ex. XML, HTML, JSON
 *
 * Building simple RESTful Web Services - Social Media Application
 *  - Retrieve all Users. GET /jpa/users
 *  - Create a User. POST /jpa/users
 *  - Retrieve one User. GET /jpa/users/{id}
 *  - Delete a User. DELETE /jpa/users/{id}
 *
 *  - Retrieve all posts for a User. GET /users/{id}/posts
 *  - Create a posts for a User. POST /users/{id}/posts
 *  - Retrieve details of a post. GET /users/{id}/posts/{post_id}
 *
 * To see documentations for this project
 *  - localhost:8081/swagger-ui.html
 *  - without swagger-ui localhost:8081/v2/api-docs
 *
 * To monitoring the API (hal-browser) - Don`t enable actuator in production
 *  - localhost:8081
 *
 * To check the data inserted in h2 database
 *  - localhost:8081/h2-console
 *  - correct JDBC URL to jdbc:h2:mem:testdb
 *
 * Best Practices
 *  - Consumer First
 *  - Create good documentation (Swagger)
 *  - Make best use of HTTP
 *  - Create appropriate response status for each type of request (200 - success, 400 - bad request, etc)
 *  - NO SECURE INFO IN URI
 *  - Use plurals. Prefer /users to /user
 *  - For exceptions define a consistent approach
 */

@SpringBootApplication
public class RestWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebServicesApplication.class, args);
	}

}
