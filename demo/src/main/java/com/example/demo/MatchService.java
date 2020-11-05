package com.example.demo;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchService {

    int createMatch(Match match);

}
