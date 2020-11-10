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

    @GetMapping("/getMatchID/{notificationID}")
    public List<Notification> getMatchID(@PathVariable int notificationID) {
        return notificationService.getMatchID(notificationID);
    }

    @PostMapping(path = "/createInvite", consumes = "application/json", produces = "application/json")
    public void createMatch(@RequestBody Notification notification){
        notificationService.createNotification(notification);
    }

    @PostMapping(path = "/updateNotificationMessage/{notificationID}/{newMessage}")
    public void setNotificationMessage(@PathVariable int notificationID, @PathVariable String newMessage) {
        notificationService.setNotificationMessage(notificationID, newMessage);
    }
}
