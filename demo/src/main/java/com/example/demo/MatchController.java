package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping(path = "/createMatch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMatch(@RequestBody Match match){
        int matchID = matchService.createMatch(match);
        return ResponseEntity.accepted().body(matchID);
    }


    @PostMapping(path = "/attemptMove", consumes = "application/json", produces = "application/json")
    public String[][] attemptMove(@RequestBody Move move){
        String[][] board = new String[8][8];
        //board[0][0] = "White King";
        return board;
    }

    @GetMapping("/getMatchesList/{accountID}")
    public List<Match> get(@PathVariable int accountID) {
        return matchService.getMatchesList(accountID);
    }

    @PatchMapping(path = "/updateMatchStatus/{matchID}/{newStatus}")
    public int setMatchStatus(@PathVariable int matchID, @PathVariable String newStatus) {
        return matchService.setStatus(matchID, newStatus);
    }
}
