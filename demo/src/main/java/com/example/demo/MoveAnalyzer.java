package com.example.demo;
import java.util.ArrayList;
import com.example.demo.chessboard.*;

public class MoveAnalyzer{

    private Integer currentPlayerID;
    private Match match;
    private ChessBoard board;
    private Move move;


    public MoveAnalyzer(Match match, ChessBoard board, Move move){
        this.board = board;
        this.move = move;
        this.currentPlayerID = move.getPlayerId();
        this.match = match;
    }

    public void checkPreconditions() throws IllegalMoveException{
        Integer turnID = this.match.getTurnID();

        if(!this.currentPlayerID.equals(turnID)) {
            throw new IllegalMoveException("Not this player's turn");
        }

        try{
            ChessPiece piece = this.board.getPiece(this.move.getFromPosition());
            if(piece == null){
                throw new IllegalMoveException("No piece at staring postion");
            }
            if(!checkPieceColor(piece)) {
                throw new IllegalMoveException("Cannot move this piece because it does not belong to this player");
            }
        }
        catch(IllegalPositionException e){
            throw new IllegalMoveException("Starting position is not on the board");
        }

        //check if player is moving themselves into check
    }

    public boolean willEndGame() throws IllegalMoveException{
        try{
            ChessPiece piece = this.board.getPiece(this.move.getToPosition());
            if(piece != null && piece instanceof King){
                return true;
            }

        }
        catch(IllegalPositionException e){
            throw new IllegalMoveException("Ending position is not on the board");
        }
        return false;
    }


    private boolean checkPieceColor(ChessPiece piece) {

        if(this.currentPlayerID.equals(this.match.getSenderID()) && piece.getColor().equals(ChessPiece.Color.WHITE)) {
            return true;
        }
        if(this.currentPlayerID.equals(this.match.getReceiverID()) && piece.getColor().equals(ChessPiece.Color.BLACK)) {
            return true;
        }
        return false;
    }
}