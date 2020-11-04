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
    public int createMatch(int account1ID, int account2ID) {
        Match match = new Match();
        match.setReceiver(account1ID);
        match.setSender(account2ID);
        Session currSession = entityManager.unwrap(Session.class);
        int test = (int) currSession.save(match);
        System.out.println(test);
        return test;
    }

}
