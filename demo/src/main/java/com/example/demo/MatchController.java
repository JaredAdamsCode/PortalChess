package com.example.demo;

import com.example.demo.chessboard.ChessBoard;
import com.example.demo.chessboard.IllegalMoveException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    @GetMapping("/getMatchesList/{accountID}")
    public List<Match> get(@PathVariable int accountID) {
        return matchService.getMatchesList(accountID);
    }

    @PatchMapping(path = "/updateMatchStatus/{matchID}/{newStatus}")
    public int setMatchStatus(@PathVariable int matchID, @PathVariable String newStatus) {
        return matchService.setStatus(matchID, newStatus);
    }


    @PatchMapping("/createBoard/{matchID}")
    public String createBoard(@PathVariable int matchID) throws JsonProcessingException {
        ChessBoard newBoard = new ChessBoard();
        newBoard.initialize();
        String storeBoard =  newBoard.getBoardString();
        matchService.createBoard(matchID,storeBoard);
        return storeBoard;
    }

    @GetMapping("/getMatch/{matchID}")
    public Match getMatch(@PathVariable int matchID) {
        return matchService.getMatch(matchID);
    }


    /* Example for updating the board with a move
    Client will send a request that contains at minimum the matchID and two moves
        First grab the Match from the database with the matchID
        Match match = matchService.getMatch(matchID);

        Get the state of the board from match into a String
        String boardString = match.getBoard();

        Send this String to then be transformed into a ChessBoard object that can then be used
        ChessBoard tempBoard = stringToObject(boardString);

        You can then use this tempBoard like you would use any sort of ChessBoard object
        System.out.println(tempBoard);
        tempBoard.move("a2","a3");
        System.out.println(tempBoard);

        If the moves given are valid you will then need to create something to update the board saved in the database with this move

        This will give you the updated ChessBoard object and turn it into a string that can be stored
        String storeBoard = tempBoard.getBoardString();

        We can then send this string and the matchID to the database to be updated, and return the updated Match object
        return matchService.updateBoard(matchID,storeBoard); -updateBoard does not exist yet

     */

    public ChessBoard stringToObject(String boardString) throws JSONException {

        JSONArray outerArray = new JSONArray(boardString);
        ChessBoard tempBoard = new ChessBoard();

        for(int i = 0; i < outerArray.length(); i++){
            JSONArray innerArray = outerArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                if(!JSONObject.NULL.equals(innerArray.get(j))){
                    JSONObject obj = innerArray.getJSONObject(j);
                    tempBoard.createChessPieceObject(obj,tempBoard);
                }
            }
        }
        return tempBoard;
    }



}
