package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationDAO {

    List<Notification> getNotificationList(Integer accountID);

    List<Notification> getPendingList(Integer accountID);

    List<Integer> getMatchID(Integer notificationID);

    int createNotification(Notification notification);

    int setNotificationMessage(int notificationID, String message);

    void createUnregisterNotification(Notification notification);

    void sendGameOverNotifications(int matchID, int winnerID, int loserID);
}
