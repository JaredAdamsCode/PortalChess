package com.example.demo;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationService {

    List<Notification> getNotificationList(Integer accountID);

    List<Notification> getPendingList(Integer accountID);

    int createNotification(Notification notification);
}
