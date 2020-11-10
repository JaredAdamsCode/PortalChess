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
    public int createNotification(Notification notification) {
        Session currSession = entityManager.unwrap(Session.class);
        return (int) currSession.save(notification);
    }

}