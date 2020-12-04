package com.example.demo;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.chessboard.ChessBoard;
import com.example.demo.chessboard.ChessPiece;
import com.example.demo.chessboard.IllegalMoveException;
import com.example.demo.chessboard.IllegalPositionException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping(path = "/createMatch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMatch(@RequestBody Match match){
        match.setReceiver_check(false);
        match.setSender_check(false);
        match.setCastlingMoves(0);
        int matchID = matchService.createMatch(match);
        return ResponseEntity.accepted().body(matchID);
    }

    @PostMapping(path = "/attemptMove", consumes = "application/json", produces = "application/json")
    public Match attemptMove(@RequestBody Move move) throws JsonProcessingException, IllegalPositionException {
        Match match = matchService.getMatch(move.getMatchId());
        String boardStr = match.getBoard();
        boolean playerCheck = false;
        boolean enemyCheck = false;


        try{

            ChessBoard board = stringToObject(boardStr);
            MoveAnalyzer moveAnalyzer = new MoveAnalyzer(match, board, move);
            board.setCastlingMoves(match.getCastlingMoves());

            moveAnalyzer.checkPreconditions();
            boolean endConditionMet = moveAnalyzer.willEndGame();
            board.move(move.getFromPosition(), move.getToPosition());

            if(moveAnalyzer.movedIntoCheck()){
                throw new IllegalMoveException("Cannot move yourself into check");
            }
            playerCheck = false;

            if(moveAnalyzer.opponentIsInCheck(board)){
                enemyCheck = true;
                if(moveAnalyzer.opponentIsMated()){
                   endConditionMet = true;
                }
            }

            if(move.getPlayerId() == match.getSenderID()){
                matchService.updateCheckStatus(match.getId(), playerCheck, enemyCheck);
            }
            else{
                matchService.updateCheckStatus(match.getId(), enemyCheck, playerCheck);
            }

            boardStr = board.getBoardString();
            Integer newTurnID = getNewTurnID(match, move.getPlayerId());
            matchService.updateBoard(move.getMatchId(), boardStr, newTurnID, match.getCastlingMoves());

        	if(endConditionMet){
        	    if(move.getPlayerId() == match.getSenderID()){
                    matchService.updateMatchResults(match.getId(), match.getSenderID(), match.getReceiverID());
                    notificationService.sendGameOverNotifications(match.getId(),  match.getSenderID(), match.getReceiverID());
                }
        	    else{
                    matchService.updateMatchResults(match.getId(), match.getReceiverID(), match.getSenderID());
                    notificationService.sendGameOverNotifications(match.getId(),  match.getReceiverID(), match.getSenderID());
                }

            }
            match = matchService.getMatch(move.getMatchId());

            match.setStatus("Legal");

        }
        catch(IllegalMoveException e){
            match.setStatus(e.getMessage());
        }
        catch(JSONException e){
            match.setStatus("Board could not be instantiated");
        }
        catch(NullPointerException e){
            match.setStatus("Illegal Move");

        }
        catch(IllegalPositionException e){
            match.setStatus(e.getMessage());
        }
        catch(JsonProcessingException e){
            throw e;

        }
        return match;
    }
    
    public Integer getNewTurnID(Match match, Integer currentPlayerID) {
    	if(currentPlayerID.equals(match.getSenderID()) ) return match.getReceiverID();
    	else return match.getSenderID();
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

    @PatchMapping("/abandonMatch/{matchID}/{winnerID}/{loserID}")
    public void abandonMatch(@PathVariable int matchID, @PathVariable int winnerID, @PathVariable int loserID) throws JsonProcessingException {
        matchService.abandonMatch(matchID,winnerID,loserID);
        notificationService.sendGameOverNotifications(matchID,  winnerID, loserID);
    }

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
