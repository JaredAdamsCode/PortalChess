package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationDAO {


    List<Notification> getNotificationList(Integer accountID);

    List<Notification> getPendingList(Integer accountID);
}
