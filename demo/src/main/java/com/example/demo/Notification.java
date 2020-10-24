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
        return "Notification [id= " + id + ", senderID= " +senderID + ", receiverID= " + receiverID + ", message=" + message + ", matchID= "
                + matchID + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(Integer id) {
        this.senderID = id;
    }

    public void setReceiver(Integer id) {
        this.receiverID = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSender() {
        return senderID;
    }

    public Integer getReceiver() {
        return receiverID;
    }

    public String getMessage(){
            return message;
    }

    public Integer getMatch(){
        return matchID;
    }

}
