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
        Query<Match> query = currSession.createQuery("from Match m where m.receiverID = :receiverID or m.senderID = :senderID")
                .setParameter("receiverID", accountID)
                .setParameter("senderID", accountID);
        return query.getResultList();
    }

    @Override
    public int setStatus(int matchID, String newStatus) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.status = :status where m.id = :mID")
                .setParameter("status", newStatus)
                .setParameter("mID", matchID);
        return query.executeUpdate();
    }

    @Override
    public void createBoard(int matchID, String storeBoard) {
        System.out.println(storeBoard);
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.board = :board where m.id = :mID")
                .setParameter("board", storeBoard)
                .setParameter("mID", matchID);

        //This line below for actually executing the query is what breaks it
        //query.executeUpdate();
    }
}
