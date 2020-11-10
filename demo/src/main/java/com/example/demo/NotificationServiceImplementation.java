package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImplementation implements  NotificationService{

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public List<Notification> getNotificationList(Integer accountID) {
        return notificationDAO.getNotificationList(accountID);
    }

    @Override
    public List<Notification> getPendingList(Integer accountID) {
        return notificationDAO.getPendingList(accountID);
    }

    @Override
    public List<Notification> getMatchID(Integer notificationID) {
        return notificationDAO.getMatchID(notificationID);
    }

    @Override
    public int createNotification(Notification notification) {
        return notificationDAO.createNotification(notification);
    }

    @Override
    public int setNotificationMessage(Integer notificationID, String message) {
        return notificationDAO.setNotificationMessage(notificationID, message);
    }
}