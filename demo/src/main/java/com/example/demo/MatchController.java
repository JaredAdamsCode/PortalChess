package com.example.demo;

import java.util.List;

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

    @PostMapping(path = "/createMatch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMatch(@RequestBody Match match){
        int matchID = matchService.createMatch(match);
        return ResponseEntity.accepted().body(matchID);
    }

    @PostMapping(path = "/attemptMove", consumes = "application/json", produces = "application/json")
    public Match attemptMove(@RequestBody Move move) throws JsonProcessingException, IllegalPositionException {
        Match match = matchService.getMatch(move.getMatchId());
        String boardStr = match.getBoard();

        try{
        	Integer currentPlayerID = move.getPlayerId();
        	Integer turnID = match.getTurnID();
        	if(!currentPlayerID.equals(turnID)) {
        		throw new IllegalMoveException("Not this player's turn");
        	}
        	Integer newTurnID = getNewTurnID(match, currentPlayerID);
            ChessBoard board = stringToObject(boardStr);
            if(!checkPieceColor(board.getPiece(move.getFromPosition()), match, currentPlayerID)) {
            	throw new IllegalMoveException("Cannot move this piece because it does not belong to this player");
            }
            board.move(move.getFromPosition(), move.getToPosition());
            boardStr = board.getBoardString();
            matchService.updateBoard(move.getMatchId(), boardStr, newTurnID);
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
        return match;
    }
    
    public Integer getNewTurnID(Match match, Integer currentPlayerID) {
    	if(currentPlayerID.equals(match.getSenderID()) ) return match.getReceiverID();
    	else return match.getSenderID();
    }
    
    public boolean checkPieceColor(ChessPiece piece, Match match, Integer currentPlayerID) {

    	if(currentPlayerID.equals(match.getSenderID()) && piece.getColor().equals(ChessPiece.Color.WHITE)) {
    		return true;
    	}
    	if(currentPlayerID.equals(match.getReceiverID()) && piece.getColor().equals(ChessPiece.Color.BLACK)) {
    		return true;
    	}
    	return false;
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
