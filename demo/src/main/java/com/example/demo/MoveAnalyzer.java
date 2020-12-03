package com.example.demo;
import java.util.ArrayList;
import com.example.demo.chessboard.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MoveAnalyzer{

    private Integer currentPlayerID;
    private Match match;
    private ChessBoard board;
    private Move move;
    private ChessPiece.Color color;


    public MoveAnalyzer(Match match, ChessBoard board, Move move){
        this.board = board;
        this.move = move;
        this.currentPlayerID = move.getPlayerId();
        this.match = match;
        this.color = getColor(move.getPlayerId());
    }

    public void checkPreconditions() throws IllegalMoveException{
        Integer turnID = this.match.getTurnID();
        ChessPiece piece = null;
        if(!this.currentPlayerID.equals(turnID)) {
            throw new IllegalMoveException("Not this player's turn");
        }

        try{
            piece = this.board.getPiece(this.move.getFromPosition());
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

    public boolean movedIntoCheck() throws IllegalPositionException{
        boolean inCheck = false;
        try{
            String kingPosition = findKing(this.color, this.board);
            inCheck = canBeCaptured(this.color, kingPosition, this.board);

        }
        catch(IllegalPositionException e){
            throw new IllegalPositionException("King could not be located");
        }
        return inCheck;
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

    private ChessPiece.Color getColor(Integer currentPlayerID){
        if(this.match.getSenderID() == currentPlayerID){
            return ChessPiece.Color.WHITE;
        }
        else{
            return ChessPiece.Color.BLACK;
        }
    }

    private ChessPiece.Color getEnemyColor(Integer currentPlayerID){
        if(this.match.getSenderID() != currentPlayerID){
            return ChessPiece.Color.WHITE;
        }
        else{
            return ChessPiece.Color.BLACK;
        }
    }

    private String findKing(ChessPiece.Color color, ChessBoard board) throws IllegalPositionException{
        boolean found = false;
        String kingLoc = "?";
        String pos;
        ChessPiece piece;
        try{
            for(int row = 1; row < 9 && !found; row++){
                for(int col = 1; col < 9 && !found; col++){
                    pos = createPositionString(row, col);

                    piece = board.getPiece(pos);
                    if(piece != null){
                        if(piece instanceof King &&  piece.getColor().equals(color)){
                            kingLoc = pos;
                            found = true;
                        }
                    }
                }
            }
        }
        catch (IllegalPositionException e){
            throw e;
        }
        //System.out.println(kingLoc);
        return kingLoc;
    }

    private boolean canBeCaptured(ChessPiece.Color color, String piecePos, ChessBoard board) throws IllegalPositionException{
        boolean inDanger = false;
        ArrayList<String> dangerSpots;
        String pos;
        ChessPiece piece;
        try{
            for(int row = 1; row < 9 && !inDanger; row++){
                for(int col = 1; col < 9 && !inDanger; col++){
                    pos = createPositionString(row, col);
                    piece = board.getPiece(pos);
                    if(piece != null && !piece.getColor().equals(color)){
                        dangerSpots = piece.legalMoves();
                        for(String s: dangerSpots){
                            //System.out.println(kingPos + " vs. " + s);
                            if(s.equals(piecePos)){
                                inDanger = true;
                            }
                        }
                    }
                }
            }
        }
        catch (IllegalPositionException e){
            throw e;
        }

        return inDanger;
    }

    public boolean opponentIsInCheck(ChessBoard board) throws IllegalPositionException{
        boolean inCheck = false;
        ChessPiece.Color color = getEnemyColor(this.currentPlayerID);
        try{
            String kingPosition = findKing(color, board);
            inCheck = canBeCaptured(color, kingPosition, board);

        }
        catch(IllegalPositionException e){
            throw new IllegalPositionException("Enemy King could not be located");
        }
        return inCheck;
    }


    public boolean opponentIsMated() throws IllegalPositionException, IllegalMoveException, JSONException, JsonProcessingException{
        boolean mated = true;

        ChessPiece.Color color = getEnemyColor(this.currentPlayerID);
        ArrayList<String> legalMoves;
        String pos;
        ChessPiece piece;
        ChessBoard boardClone;

        try{
            String boardStr = this.board.getBoardString();
            for(int row = 1; row < 9 && mated; row++){
                for(int col = 1; col < 9 && mated; col++){
                    pos = createPositionString(row, col);
                    piece = this.board.getPiece(pos);
                    if(piece != null && piece.getColor().equals(color)){
                        legalMoves = piece.legalMoves();
                        for(String s: legalMoves){
                            boardClone = stringToObject(boardStr);
                            boardClone.move(pos, s);
                            if(opponentIsInCheck(boardClone) == false){
                                mated = false;
                            }
                        }
                    }
                }
            }
        }
        catch (IllegalPositionException e){
            throw e;
        }
        catch (IllegalMoveException e){
            throw e;
        }
        catch (JSONException e){
            throw e;
        }
        catch(JsonProcessingException e){
            throw e;
        }


        return mated;
    }


    private String createPositionString(int row, int col) {
        char columnChar = (char) (col + 96);
        String position = "";
        position += columnChar;
        position += row;
        return position;
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