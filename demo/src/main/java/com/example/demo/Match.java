package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

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

    @Column
    private Boolean sender_check;

    @Column
    private Boolean receiver_check;
    
    @Column 
    private Integer castling_moves;


    @Column
    private Timestamp start_time;

    @Column
    private Timestamp end_time;

    @Override
     public String toString() {
        return "Match [id = " + id + ", senderId = " + senderID + ", receiverId = " + receiverID + ", status = " + status + ", startTime = " + start_time + ", endTime = " + end_time + "]";
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

    public void setStartTime(Timestamp startTime){this.start_time = startTime;}

    public void setEndTime(Timestamp endTime){this.end_time = endTime;}

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

    public Timestamp getStartTime(){return start_time;}

    public Timestamp getEndTime(){return end_time;}

    public void setTurnID(Integer turnID) {
    	this.turnID = turnID;
    }

    public Boolean getSender_check() {
        return sender_check;
    }

    public void setSender_check(Boolean sender_check) {
        this.sender_check = sender_check;
    }

    public Boolean getReceiver_check() {
        return receiver_check;
    }

    public void setReceiver_check(Boolean receiver_check) {
        this.receiver_check = receiver_check;
    }

	public Integer getCastlingMoves() {
		return castling_moves;
	}

	public void setCastlingMoves(Integer castlingMoves) {
		this.castling_moves = castlingMoves;
	}
    
    

}
