package com.example.demo;


public class Move{
    private String fromPosition;
    private String toPosition;
    private Integer matchId;
    private Integer playerId;

    Move(String fromPosition, String toPosition, Integer matchId, Integer playerId){
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.matchId = matchId;
        this.playerId = playerId;

    }

    Move(){
        this.fromPosition = "invalid";
        this.toPosition = "invalid";
        this.matchId = Integer.valueOf(-1);
        this.playerId = Integer.valueOf(-1);
    }

    public String getFromPosition(){
        return this.fromPosition;
    }

    public String getToPosition(){
        return this.toPosition;
    }

    public Integer getMatchId(){
        return this.matchId;
    }

    public Integer getPlayerId(){
        return this.playerId;
    }
}