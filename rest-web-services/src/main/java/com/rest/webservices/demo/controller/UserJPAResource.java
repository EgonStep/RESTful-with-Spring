package com.rest.webservices.demo.controller;

import com.rest.webservices.demo.exception_handling.UserNotFoundException;
import com.rest.webservices.demo.model.Post;
import com.rest.webservices.demo.model.User;
import com.rest.webservices.demo.model.dao.PostRepository;
import com.rest.webservices.demo.model.dao.UserDAOService;
import com.rest.webservices.demo.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retrieveOneUser(@PathVariable int id) {
        // Optional will return a proper object even if the user doesnt exist in database
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            throw new UserNotFoundException("id - " + id);

        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        return ResponseEntity
                .created(returnLocation(savedUser.getId()))
                .build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteOneUser(@PathVariable int id) {
        // If fail, it will throw automatically an exception
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/post")
    public List<Post> retrieveAllPostFromUsers(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            throw new UserNotFoundException("id - " + id);

        return user.get().getPost();
    }

    @PostMapping(path = "/jpa/users/{id}/post")
    public ResponseEntity<Object> createNewPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id - " + id);

        post.setUser(user.get());
        postRepository.save(post);

        return ResponseEntity
                .created(returnLocation(post.getId()))
                .build();
    }

    private URI returnLocation(Integer id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
