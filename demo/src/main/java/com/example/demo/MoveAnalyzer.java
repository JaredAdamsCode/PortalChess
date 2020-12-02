package com.example.demo;
import java.util.ArrayList;
import com.example.demo.chessboard.*;

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
            String kingPosition = findKing(this.color);
            inCheck = isInCheck(this.color, kingPosition);

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

    private String findKing(ChessPiece.Color color) throws IllegalPositionException{
        boolean found = false;
        String kingLoc = "?";
        String pos;
        ChessPiece piece;
        try{
            for(int row = 1; row < 9 && !found; row++){
                for(int col = 1; col < 9 && !found; col++){
                    pos = createPositionString(row, col);

                    piece = this.board.getPiece(pos);
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

    private boolean isInCheck(ChessPiece.Color color, String kingPos) throws IllegalPositionException{
        boolean inCheck = false;
        ArrayList<String> dangerSpots;
        String pos;
        ChessPiece piece;
        try{
            for(int row = 1; row < 9 && !inCheck; row++){
                for(int col = 1; col < 9 && !inCheck; col++){
                    pos = createPositionString(row, col);
                    piece = this.board.getPiece(pos);
                    if(piece != null && !piece.getColor().equals(color)){
                        dangerSpots = piece.legalMoves();
                        for(String s: dangerSpots){
                            //System.out.println(kingPos + " vs. " + s);
                            if(s.equals(kingPos)){
                                inCheck = true;
                            }
                        }
                    }
                }
            }
        }
        catch (IllegalPositionException e){
            throw e;
        }

        return inCheck;
    }

    private String createPositionString(int row, int col) {
        char columnChar = (char) (col + 96);
        String position = "";
        position += columnChar;
        position += row;
        return position;
    }
}