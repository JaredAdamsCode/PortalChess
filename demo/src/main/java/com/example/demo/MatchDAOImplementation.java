package com.example.demo;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MatchDAOImplementation implements  MatchDAO{

    @Autowired
    private EntityManager entityManager;


    @Override
    public int createMatch(Match match) {
        Session currSession = entityManager.unwrap(Session.class);
        return (int) currSession.save(match);
    }

    @Override
    public List<Match> getMatchesList(int accountID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("from Match n where n.receiverID = :receiverID").setParameter("receiverID", accountID);
        return query.getResultList();
    }

}