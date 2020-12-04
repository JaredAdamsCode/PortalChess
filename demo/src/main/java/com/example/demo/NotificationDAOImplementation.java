package com.example.demo;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class NotificationDAOImplementation implements  NotificationDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Notification> getNotificationList(Integer accountID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Notification> query = currSession.createQuery("from Notification n where n.receiverID = :receiverID").setParameter("receiverID", accountID);
        return query.getResultList();
    }

    @Override
    public List<Notification> getPendingList(Integer accountID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Notification> query = currSession.createQuery("from Notification n where n.senderID = :senderID").setParameter("senderID", accountID);
        return query.getResultList();
    }

    @Override
    public List<Integer> getMatchID(Integer notificationID) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Integer> query = currSession.createQuery("select matchID from Notification n where n.id = :nID")
                .setParameter("nID", notificationID);
        return query.getResultList();
    }

    @Override
    public int createNotification(Notification notification) {
        Session currSession = entityManager.unwrap(Session.class);
        return (int) currSession.save(notification);
    }

    @Override
    public int setNotificationMessage(int notificationID, String message) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Notification> query = currSession.createQuery("update Notification n set n.message = :message where n.id = :nID")
                .setParameter("message", message)
                .setParameter("nID", notificationID);
        return query.executeUpdate();
    }

    @Override
    public void createUnregisterNotification(Notification notification) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.save(notification);
    }

    @Override
    public void sendGameOverNotifications(int matchID, int winnerID, int loserID){
        Session currSession = entityManager.unwrap(Session.class);

        Account winnerAccount = currSession.get(Account.class, winnerID);
        String winnerName = winnerAccount.getUsername();
        Account loserAccount = currSession.get(Account.class, loserID);
        String loserName = loserAccount.getUsername();
        String winnerMsg = "You won your game against " + loserName;
        String loserMsg = "You lost your game against " + winnerName;
        Notification winnerNote = new Notification(matchID + 1000, winnerID, winnerID, winnerMsg, matchID);
        Notification loserNote = new Notification(matchID + 1001, loserID, loserID, loserMsg, matchID);

        currSession.save(winnerNote);
        currSession.save(loserNote);

        //System.out.println("hello from sendGameOverNotifications");
    }

}
