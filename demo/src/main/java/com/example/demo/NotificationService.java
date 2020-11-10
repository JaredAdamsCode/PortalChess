package com.example.demo;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationService {

    List<Notification> getNotificationList(Integer accountID);

    List<Notification> getPendingList(Integer accountID);

    List<Notification> getMatchID(Integer notificationID);

    int createNotification(Notification notification);

    void setNotificationMessage(Integer notificationID, String message);
}
