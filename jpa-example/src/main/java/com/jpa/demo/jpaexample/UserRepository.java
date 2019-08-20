package com.jpa.demo.jpaexample;

import com.jpa.demo.jpaexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// When extends the JpaRepository we have access to all methods to communicate to the database
public interface UserRepository extends JpaRepository<User, Long> {

}
