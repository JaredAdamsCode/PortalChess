package com.example.demo.chessboard;

import java.util.ArrayList;

public class King extends ChessPiece{

	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2654";
		}
		else {
			return "\u265A";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		// check up
		checkKingMove(this.row + 1, this.column, moves);
		// check up right
		checkKingMove(this.row + 1, this.column + 1, moves);
		// check right
		checkKingMove(this.row, this.column + 1, moves);
		// check down right
		checkKingMove(this.row - 1, this.column + 1, moves);
		// check down
		checkKingMove(this.row - 1, this.column, moves);
		// check down left
		checkKingMove(this.row - 1, this.column - 1, moves);
		// check left
		checkKingMove(this.row, this.column - 1, moves);
		// check up left
		checkKingMove(this.row + 1, this.column - 1, moves);
		// reutrn arraylist
		return moves;
	}
	
	private void checkKingMove(int row, int col, ArrayList<String> moves) {
		if(row <= 8 && row >= 1 && col <= 8 && col >= 1) {
			String position = createPositionString(row, col);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor()) {
					moves.add(position);
				}

			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
