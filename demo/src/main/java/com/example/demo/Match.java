package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")

public class Match {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column
     private Integer id;

    @Column
     private Integer senderID;

    @Column
     private Integer receiverID;

    @Column
    private String status;

    @Override
     public String toString() {
        return "Match [id = " + id + ", senderId = " + senderID + ", receiverId = " + receiverID + ", status = " + status + "]";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSenderID(Integer senderId) {
        this.senderID = senderId;
    }

    public void setReceiverID(Integer receiverId) {
        this.receiverID = receiverId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public String getStatus() {
        return status;
    }

}