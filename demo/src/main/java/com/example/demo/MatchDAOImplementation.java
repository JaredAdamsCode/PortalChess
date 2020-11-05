package com.example.demo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MatchDAOImplementation implements  MatchDAO{

    @Autowired
    private EntityManager entityManager;


    @Override
    public int createMatch(Match match) {
        Session currSession = entityManager.unwrap(Session.class);
        return (int) currSession.save(match);
    }

}
