package com.example.demo.chessboard;

import java.util.ArrayList;
import java.util.Arrays;

public class Portal extends ChessPiece {

    public enum Status {PORTAL, BLACK_HOLE};

    protected Status status;

    public Portal(ChessBoard board, Color color, String type, Status status) {
        super(board, color, type);
        this.status = status;
    }

    @Override
    public String toString() {
        if(this.getColor() == Color.WHITE) {
            if (this.getStatus() == Status.BLACK_HOLE) {
                return "\u25c9";
            }else {
                return "\u25ce";
            }
        }else {
            if(this.getStatus() == Status.BLACK_HOLE) {
                return "\u25a3";
            }else {
                return "\u25a2";
            }
        }
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<String>();

        if(this.getStatus() != Status.BLACK_HOLE) {
            checkMoves(moves);
        }

        // return arraylist
        return moves;
    }

    private void checkMoves(ArrayList<String> moves) {
        // check up
        checkPortalMove(this.row + 1, this.column, moves);
        // check up right
        checkPortalMove(this.row + 1, this.column + 1, moves);
        // check right
        checkPortalMove(this.row, this.column + 1, moves);
        // check down right
        checkPortalMove(this.row - 1, this.column + 1, moves);
        // check down
        checkPortalMove(this.row - 1, this.column, moves);
        // check down left
        checkPortalMove(this.row - 1, this.column - 1, moves);
        // check left
        checkPortalMove(this.row, this.column - 1, moves);
        // check up left
        checkPortalMove(this.row + 1, this.column - 1, moves);
    }

    private void checkPortalMove(int row, int col, ArrayList<String> moves) {
        if(row <= 8 && row >= 1 && col <= 8 && col >= 1) {
            String position = createPositionString(row, col);
            try {
                ChessPiece checkPiece = board.getPiece(position);
                if(checkPiece == null || checkPiece instanceof Portal ||
                        (checkPiece != null && this instanceof Portal)) {
                    moves.add(position);
                }
            } catch (IllegalPositionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

//    public ArrayList<String> getValidInitPlacements() {
//        if(this.getColor() == Color.WHITE) {
//            return new ArrayList<String> (
//                    Arrays.asList("a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4")
//            );
//        }else {
//            return new ArrayList<String> (
//                    Arrays.asList("a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5")
//            );
//        }
//    }
//
//    public boolean isValidInitPlacement(String position) {
//        if(this.getValidInitPlacements().contains(position)) {
//            return true;
//        }
//        return false;
//    }
}