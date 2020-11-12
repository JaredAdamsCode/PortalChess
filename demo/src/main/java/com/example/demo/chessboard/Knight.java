package com.example.demo.chessboard;
import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2658";
		}
		else {
			return "\u265E";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		return moves;
	}

}
