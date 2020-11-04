package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchService {

    int createMatch(int account1ID, int account2ID);

}
