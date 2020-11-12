package com.example.demo.chessboard;

import java.util.ArrayList;

public abstract class ChessPiece {
	

	public enum Color {WHITE, BLACK};
	
	protected ChessBoard board = null;
	
	// row and column are stored as the literal position - not adjusted for array values. They are +1 from array positions. 
	protected int row;
	
	protected int column;
	
	protected Color color;
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	public Color getColor() {	
		return this.color;
	}
	
	public String getPosition() {
		char columnChar = (char) (column + 96);
		String position = "";
		position += columnChar;
		position += row;
		return position;
	}
	
	public void setPosition(String position) throws IllegalPositionException{
		if(position.length() != 2) {
			throw new IllegalPositionException("Illegal position exception: position has incorrect length: " + position);
		}
		int tempRow = Character.getNumericValue(position.charAt(1));
		int tempColumn = position.charAt(0) - 96;
		if(tempRow > 8 || tempRow < 1 || tempColumn > 8 || tempColumn < 1) {
			throw new IllegalPositionException("Illegal position exception: cannot get piece at position: " + position);
		}
		else {
			this.row = tempRow;
			this.column = tempColumn;
		}		
	}
	
	abstract public String toString();
	
	abstract public ArrayList<String> legalMoves();
	
	protected String createPositionString(int row, int col) {
		char columnChar = (char) (col + 96);
		String position = "";
		position += columnChar;
		position += row;
		return position;
	}
	
	
	
}
