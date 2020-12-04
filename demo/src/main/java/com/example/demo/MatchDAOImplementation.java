package com.example.demo;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Date;
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

    @Transactional
    @Override
    public void updateBoard(int matchID, String storeBoard, int newTurnID, int cMoves) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.board = :board, m.turnID = :turnID, m.castlingMoves = :castlingMoves where m.id = :mID")
                .setParameter("board", storeBoard)
                .setParameter("turnID", newTurnID)
                .setParameter("castlingMoves", cMoves)
                .setParameter("mID", matchID);
        query.executeUpdate();
    }
   
    @Transactional
    @Override
    public void createBoard(int matchID, String storeBoard) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.board = :board where m.id = :mID")
                .setParameter("board", storeBoard)
                .setParameter("mID", matchID);
        query.executeUpdate();
    }

    @Override
    public Match getMatch(int matchID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("from Match m where m.id = :mID")
                .setParameter("mID", matchID);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void abandonMatch(int matchID, int winnerID, int loserID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.winner = :pID where m.id = :mID")
                .setParameter("pID", winnerID)
                .setParameter("mID", matchID);
        query.executeUpdate();
        query = currSession.createQuery("update Match m set m.loser = :pID where m.id = :mID")
                .setParameter("pID", loserID)
                .setParameter("mID", matchID);
        query.executeUpdate();
        query = currSession.createQuery("update Match m set m.status = :status where m.id = :mID")
                .setParameter("status", "abandoned")
                .setParameter("mID", matchID);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void updateMatchResults(int matchID, int winnerID, int loserID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.winner = :pID where m.id = :mID")
                .setParameter("pID", winnerID)
                .setParameter("mID", matchID);
        query.executeUpdate();
        query = currSession.createQuery("update Match m set m.loser = :pID where m.id = :mID")
                .setParameter("pID", loserID)
                .setParameter("mID", matchID);
        query.executeUpdate();
        query = currSession.createQuery("update Match m set m.status = :status where m.id = :mID")
                .setParameter("status", "finished")
                .setParameter("mID", matchID);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void updateCheckStatus(int matchID, boolean senderCheck, boolean receiverCheck){
        Session currSession = entityManager.unwrap(Session.class);
        Query<Match> query = currSession.createQuery("update Match m set m.sender_check = :sCheck where m.id = :mID")
                .setParameter("sCheck", senderCheck)
                .setParameter("mID", matchID);
        query.executeUpdate();
        query = currSession.createQuery("update Match m set m.receiver_check = :rCheck where m.id = :mID")
                .setParameter("rCheck", receiverCheck)
                .setParameter("mID", matchID);
        query.executeUpdate();
    }
}
