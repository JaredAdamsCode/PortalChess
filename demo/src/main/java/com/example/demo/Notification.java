package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "notifications")

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer senderID;

    @Column
    private Integer receiverID;

    @Column
    private String message;

    @Column
    private Integer matchID;

    @Override
    public String toString() {
        return "Notification [id = " + id + ", senderID = " + senderID + ", receiverID = " + receiverID + ", message=" + message + ", matchID = "
                + matchID + "]";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(Integer senderID) {
        this.senderID = senderID;
    }

    public void setReceiver(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMatch(Integer matchID){this.matchID = matchID;}

    public Integer getId() {
        return id;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public String getMessage(){
            return message;
    }

    public Integer getMatchID(){
        return matchID;
    }

}
