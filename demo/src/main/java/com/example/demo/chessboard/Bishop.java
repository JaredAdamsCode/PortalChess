package com.example.demo.chessboard;

import java.util.ArrayList;

public class Bishop extends ChessPiece{

	public Bishop(ChessBoard board, Color color, String type) {
		super(board, color, type);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2657";
		}
		else {
			return "\u265D";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		int bishopRow;
		int bishopColumn;
		
		// check up and right
		bishopRow = this.row + 1;
		bishopColumn = this.column + 1;
		while(bishopRow <= 8 && bishopColumn <= 8) {
			String position = createPositionString(bishopRow, bishopColumn);
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
			bishopRow++;
			bishopColumn++;
		}
		// check up and left
		bishopRow = this.row + 1;
		bishopColumn = this.column - 1;
		while(bishopRow <= 8 && bishopColumn >= 1) {
			String position = createPositionString(bishopRow, bishopColumn);
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
			bishopRow++;
			bishopColumn--;
		}
		// check down and left
		bishopRow = this.row - 1;
		bishopColumn = this.column - 1;
		while(bishopRow >= 1 && bishopColumn >= 1) {
			String position = createPositionString(bishopRow, bishopColumn);
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
			bishopRow--;
			bishopColumn--;
		}
		// check down and right
		bishopRow = this.row - 1;
		bishopColumn = this.column + 1;
		while(bishopRow >= 1 && bishopColumn <= 8) {
			String position = createPositionString(bishopRow, bishopColumn);
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
			bishopRow--;
			bishopColumn++;
		}
		return moves;
	}

}
