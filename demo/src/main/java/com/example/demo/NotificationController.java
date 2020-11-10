package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getInviteList/{accountID}")
    public List<Notification> get(@PathVariable int accountID) {
        return notificationService.getNotificationList(accountID);
    }

    @GetMapping("/getPendingList/{accountID}")
    public List<Notification> getPending(@PathVariable int accountID) {
        return notificationService.getPendingList(accountID);
    }

    @PostMapping(path = "/createInvite", consumes = "application/json", produces = "application/json")
    public void createMatch(@RequestBody Notification notification){
        notificationService.createNotification(notification);
    }

}