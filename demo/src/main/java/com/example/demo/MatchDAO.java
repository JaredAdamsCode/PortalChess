package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public interface MatchDAO {

    int createMatch(Match match);

}
