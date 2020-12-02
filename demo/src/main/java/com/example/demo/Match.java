package com.example.demo;

import com.example.demo.chessboard.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

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

    @Column
    private Integer winner;

    @Column
    private Integer loser;

    @Column
    @SerializedName("chessboard")
    private String board;

    @Column
    private Integer turnID;
/*
    @Column
    private Boolean senderCheck;

    @Column
    private Boolean receiverCheck;*/

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

    public void setWinner(Integer winnerID) { this.winner = winnerID; }

    public void setLoser(Integer loserID) { this.loser = loserID; }

    public void setBoard(String board){this.board = board;}

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

    public Integer getWinner() {return winner; }

    public Integer getLoser() {return loser; }

    public String getBoard(){return board;}
    
    public Integer getTurnID() {
        return turnID;
    }
    
    public void setTurnID(Integer turnID) {
    	this.turnID = turnID;
    }

    /*public Boolean getSenderCheck() {
        return senderCheck;
    }

    public void setSenderCheck(Boolean senderCheck) {
        this.senderCheck = senderCheck;
    }

    public Boolean getReceiverCheck() {
        return receiverCheck;
    }

    public void setReceiverCheckCheck(Boolean receiverCheck) {
        this.receiverCheck = receiverCheck;
    }*/

}
