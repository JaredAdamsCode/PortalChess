package com.example.demo.chessboard;

import java.util.ArrayList;

public class Rook extends ChessPiece{

	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2656";
		}
		else {
			return "\u265C";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		//check up
		for(int i = this.row + 1; i <= 8; i++) {
			String position = createPositionString(i, this.column);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor()) {
					moves.add(position);
					break;
				}
				if(checkPiece != null && checkPiece.getColor() == this.getColor()) {
					break;
				}
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// check down
		for(int i = this.row - 1; i >= 1; i--) {
			String position = createPositionString(i, this.column);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor()) {
					moves.add(position);
					break;
				}
				if(checkPiece != null && checkPiece.getColor() == this.getColor()) {
					break;
				}
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// check right
		for(int i = this.column + 1; i <= 8; i++) {
			String position = createPositionString(this.row, i);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor()) {
					moves.add(position);
					break;
				}
				if(checkPiece != null && checkPiece.getColor() == this.getColor()) {
					break;
				}
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// check left
		for(int i = this.column - 1; i >= 1; i--) {
			String position = createPositionString(this.row, i);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor()) {
					moves.add(position);
					break;
				}
				if(checkPiece != null && checkPiece.getColor() == this.getColor()) {
					break;
				}
			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return moves;
	}
	

}
