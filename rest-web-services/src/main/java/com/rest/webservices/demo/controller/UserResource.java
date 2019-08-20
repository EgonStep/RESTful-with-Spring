package com.rest.webservices.demo.controller;

import com.rest.webservices.demo.exception_handling.UserNotFoundException;
import com.rest.webservices.demo.model.User;
import com.rest.webservices.demo.model.dao.UserDAOService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

// UserResource = UserController

@RestController
public class UserResource {

    private final UserDAOService service;

    public UserResource(UserDAOService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieveOneUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null)
            throw new UserNotFoundException("id - " + id);

        // Use HATEOAS dependency to create links to methods on JSON response
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));

        return resource;
    }

    // We use Postman API to send the post request
    @PostMapping(path = "/users")
    // @RequestBody whatever is passed in request body it`ll be set as parameter for the method
    // Best method is, when the user is created, we return a Created HTTP Status
    // @Valid to validate the fields from User class
    public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Build the servlet for the current post request
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri(); // return the user id that was created

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public User deleteOneUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null)
            throw new UserNotFoundException("id - " + id);

        return user;
    }
}
