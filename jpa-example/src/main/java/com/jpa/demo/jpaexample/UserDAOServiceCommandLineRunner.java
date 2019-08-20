package com.jpa.demo.jpaexample;

import com.jpa.demo.jpaexample.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        User user = new User("Jack", "Admin");
        userRepository.save(user);
        logger.info("New User is created: " + user);

        Optional<User> userCreated = userRepository.findById(1L);
        logger.info("User is retrieved: " + userCreated);

        List<User> users = userRepository.findAll();
        logger.info("All users: " + users);
    }
}
