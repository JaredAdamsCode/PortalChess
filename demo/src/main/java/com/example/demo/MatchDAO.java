package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchDAO {

    int createMatch(Match match);

    List<Match> getMatchesList(int accountID);

    int setStatus(int matchID, String newStatus);

    void createBoard(int matchID, String storeBoard);

    void updateBoard(int matchID, String storeBoard, int newTurnID);

    Match getMatch(int matchID);

    void abandonMatch(int matchID, int winnerID, int loserID);

    void updateMatchResults(int matchID, int winnerID, int loserID);

    void updateCheckStatus(int matchID, boolean senderCheck, boolean receiverCheck);
}
