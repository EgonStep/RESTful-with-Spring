package com.jpa.demo.jpaexample.service;

import com.jpa.demo.jpaexample.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository // Interact with the database
@Transactional
public class UserDAOService {

    // To save data in the database
    @PersistenceContext
    private EntityManager entityManager;

    public long insert(User user){
        entityManager.persist(user);
        return user.getId();
    }
}
