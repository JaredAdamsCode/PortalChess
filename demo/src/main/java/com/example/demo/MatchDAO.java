package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchDAO {

    int createMatch(Match match);

    List<Match> getMatchesList(int accountID);

    int setStatus(int matchID, String newStatus);

    void createBoard(int matchID, String storeBoard);

    Match getMatch(int matchID);
}
