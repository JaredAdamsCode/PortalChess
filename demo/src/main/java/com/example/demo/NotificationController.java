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
    public List<Integer> getMatchID(@PathVariable int notificationID) {
        return notificationService.getMatchID(notificationID);
    }

    @PostMapping(path = "/createInvite", consumes = "application/json", produces = "application/json")
    public void createMatch(@RequestBody Notification notification){
        notificationService.createNotification(notification);
    }

    @PatchMapping(path = "/updateNotificationMessage/{notificationID}/{newMessage}")
    public int setNotificationMessage(@PathVariable int notificationID, @PathVariable String newMessage) {
        return notificationService.setNotificationMessage(notificationID, newMessage);
    }

    @PostMapping(path = "/unregisterNotification/{userID}")
    public void createUnregisterNotification(@PathVariable int userID) {
        Notification notification = new Notification();
        notification.setSender(userID);
        notification.setReceiver(1);
        notification.setMessage("A user that you had an active match with has unregistered their account.");
        notificationService.createUnregisterNotification(notification);
    }

    @PostMapping(path = "/gameOver/matchID}/{winnerID}/{loserID}")
    public void sendGameOverNotifications(@PathVariable int matchID, @PathVariable int winnerID, @PathVariable int loserID){
        notificationService.sendGameOverNotifications(matchID,winnerID,loserID);
    }

}
