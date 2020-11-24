package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchServiceImplementation implements MatchService{

    @Autowired
    private MatchDAO matchDAO;

    @Override
    public int createMatch(Match match) {
        return matchDAO.createMatch(match);
    }

    @Override
    public List<Match> getMatchesList(int accountID) {return matchDAO.getMatchesList(accountID);}

    @Override
    @Transactional
    public int setStatus(int matchID, String newStatus) { return matchDAO.setStatus(matchID, newStatus); }

    @Override
    public void createBoard(int matchID, String storeBoard) { matchDAO.createBoard(matchID, storeBoard);}

    @Override
    public Match getMatch(int matchID) { return matchDAO.getMatch(matchID);}

    @Override
    public void abandonMatch(int matchID, int playerID) { matchDAO.abandonMatch(matchID, playerID); }
}
