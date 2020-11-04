package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImplementation implements MatchService{

    @Autowired
    private MatchDAO matchDAO;


    @Override
    public int createMatch(int account1ID, int account2ID) {
        return matchDAO.createMatch(account1ID,account2ID);
    }
}
