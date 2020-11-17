package com.example.demo.chessboard;

import java.util.ArrayList;

public class Queen extends ChessPiece{

	public Queen(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2655";
		}
		else {
			return "\u265B";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		return moves;
	}

}
