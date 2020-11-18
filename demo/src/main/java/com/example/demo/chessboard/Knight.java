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
		// check up 2 right 1
		checkKnightMove(this.row + 2, this.column + 1, moves);
		// check up 1 right 2
		checkKnightMove(this.row + 1, this.column + 2, moves);
		// check down 1 right 2
		checkKnightMove(this.row - 1, this.column + 2, moves);
		// check down 2 right 1
		checkKnightMove(this.row - 2, this.column + 1, moves);
		// check down 2 left 1
		checkKnightMove(this.row - 2, this.column - 1, moves);
		// check down 1 left 2
		checkKnightMove(this.row - 1, this.column - 2, moves);
		// check up 1 left 2
		checkKnightMove(this.row + 1, this.column - 2, moves);
		// check up 2 left 1
		checkKnightMove(this.row + 2, this.column - 1, moves);
		// reutrn arraylist
		return moves;
	}
			
	private void checkKnightMove(int row, int col, ArrayList<String> moves) {
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
