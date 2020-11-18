package com.example.demo.chessboard;

import java.util.ArrayList;

public class Queen extends ChessPiece{

	public Queen(ChessBoard board, Color color, String type) {
		super(board, color, type);
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
		
		checkUp(moves);
		checkDown(moves);
		checkRight(moves);
		checkLeft(moves);
		checkUpLeft(moves);
		checkUpRight(moves);
		checkDownLeft(moves);
		checkDownRight(moves);
		
		return moves;
	}
	
	public void checkUp(ArrayList<String> moves) {
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
	}
	
	public void checkDown(ArrayList<String> moves) {
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
	}
	
	public void checkRight(ArrayList<String> moves) {
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
	}
	
	public void checkLeft(ArrayList<String> moves) {
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
	}

	public void checkUpRight(ArrayList<String> moves) {
		int queenRow = this.row + 1;
		int queenColumn = this.column + 1;
		
		while(queenRow <= 8 && queenColumn <= 8) {
			String position = createPositionString(queenRow, queenColumn);
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
			queenRow++;
			queenColumn++;
		}
	}
	
	public void checkUpLeft(ArrayList<String> moves) {
		int queenRow = this.row + 1;
		int queenColumn = this.column - 1;
		while(queenRow <= 8 && queenColumn >= 1) {
			String position = createPositionString(queenRow, queenColumn);
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
			queenRow++;
			queenColumn--;
		}
	}
		
	public void checkDownLeft(ArrayList<String> moves) {
		int queenRow = this.row - 1;
		int queenColumn = this.column - 1;
		while(queenRow >= 1 && queenColumn >= 1) {
			String position = createPositionString(queenRow, queenColumn);
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
			queenRow--;
			queenColumn--;
		}
	}
	
	public void checkDownRight(ArrayList<String> moves) {
		int queenRow = this.row - 1;
		int queenColumn = this.column + 1;
		while(queenRow >= 1 && queenColumn <= 8) {
			String position = createPositionString(queenRow, queenColumn);
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
			queenRow--;
			queenColumn++;
		}
	}


}
