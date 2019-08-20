package com.rest.webservices.demo.model.dao;

import com.rest.webservices.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // What entity will be managing JpaRepository<Entity, Primary Key>
public interface UserRepository extends JpaRepository<User, Integer> {
}
