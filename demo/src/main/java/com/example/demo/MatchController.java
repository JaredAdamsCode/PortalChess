package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping(path = "/createMatch", consumes = "application/json")
    public int createMatch(@RequestBody Match match){
        System.out.println(match);
       // return matchService.createMatch(account1ID,account2ID);
        return 0;
    }
}
