package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchDAO {

    int createMatch(Match match);

    List<Match> getMatchesList(int accountID);
}