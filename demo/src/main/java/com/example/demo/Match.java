package com.example.demo;

import com.example.demo.chessboard.ChessBoard;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;

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
    private Timestamp startTime;

    @Column
    private Timestamp endTime;

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

    public void setStartTime(Timestamp timestamp) {this.startTime = timestamp;}

    public void setEndTime(Timestamp timestamp) {this.endTime = timestamp;}

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

    public Timestamp getStartTime() { return startTime; }

    public Timestamp getEndTime() { return endTime; }

}
