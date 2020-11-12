package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchService {

    int createMatch(Match match);

    List<Match> getMatchesList(int accountID);

    int setStatus(int matchID, String newStatus);

    void createBoard(int matchID, String storeBoard);
}
