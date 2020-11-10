package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
