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

    @Override
    public String toString() {
        return "Match [id= " + id + ", senderID= " + senderID + ", receiverID= " + receiverID + "]";
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

    public Integer getId() {
        return id;
    }

    public Integer getSender() {
        return senderID;
    }

    public Integer getReceiver() {
        return receiverID;
    }

}
