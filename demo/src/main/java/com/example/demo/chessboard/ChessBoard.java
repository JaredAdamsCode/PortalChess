package com.example.demo.chessboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class ChessBoard {
	
	private ChessPiece[][] board;
	
	public ChessBoard() {
		this.board = new ChessPiece[8][8];
	}
	
	public void initialize() {
		createPawns();
		createRooks();
		createKnights();
		createBishops();
		createQueens();
		createKings();
	}
	
	public ChessPiece getPiece(String position) throws IllegalPositionException{
		if(position.length() != 2) {
			throw new IllegalPositionException("Illegal position exception: position has incorrect length: " + position);
		}
		int tempRow = Character.getNumericValue(position.charAt(1));
		int tempColumn = position.charAt(0) - 96;
		if(tempRow > 8 || tempRow < 1 || tempColumn > 8 || tempColumn < 1) {
			throw new IllegalPositionException("Illegal position exception: position out of bounds: " + position);
		}
		else {
			ChessPiece piece = board[tempRow - 1][tempColumn - 1];
			return piece;	
		}
	}
	
	public boolean placePiece(ChessPiece piece, String position) {
		// check illegal characters in position
		if(position.length() != 2) {
			return false;
		}
		
		// check out of bounds
		int tempRow = Character.getNumericValue(position.charAt(1));
		int tempColumn = position.charAt(0) - 96;				
		if(tempRow > 8 || tempRow < 1 || tempColumn > 8 || tempColumn < 1) {
			return false;
		}
		
		// check same color
		ChessPiece pieceAtPosition = board[tempRow - 1][tempColumn - 1];
		if(pieceAtPosition != null && pieceAtPosition.getColor() == piece.getColor()) {
			return false;
		}
		
		// conditions pass, attempt to setPiece and return true
		try {
			piece.setPosition(position);
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
		board[tempRow - 1][tempColumn - 1] = piece;
		return true;	

	}
	
	public void move(String fromPosition, String toPosition) throws IllegalMoveException{
		int fromRow = Character.getNumericValue(fromPosition.charAt(1));
		int fromColumn = fromPosition.charAt(0) - 96;
		int toRow = Character.getNumericValue(toPosition.charAt(1));
		int toColumn = toPosition.charAt(0) - 96;
		ArrayList<String> legalMoves;
		ChessPiece fromPiece;
		ChessPiece toPiece;
		try {
			fromPiece = getPiece(fromPosition);
			toPiece = getPiece(toPosition);
			legalMoves = fromPiece.legalMoves();
			if(legalMoves.contains(toPosition) && (toPiece == null || toPiece.color != fromPiece.color)) {
				placePiece(fromPiece, toPosition);
				board[fromRow - 1][fromColumn - 1] = null;
			}
			else {
				throw new IllegalMoveException("Cannot make move from " + fromPosition + " to " + toPosition);
			}
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}

	private String createPositionString(int row, int col) {
		char columnChar = (char) (col + 96);
		String position = "";
		position += columnChar;
		position += row;
		return position;
	}
	
	private void createPawns() {
		// create WHITE pawns
		String position;
		for(int i = 1; i <= 8; i++) {
			Pawn pawn = new Pawn(this, ChessPiece.Color.WHITE);
			position = createPositionString(2, i);
			placePiece(pawn, position);
		}
		// create BLACK pawns
		for(int i = 1; i <= 8; i++) {
			Pawn pawn = new Pawn(this, ChessPiece.Color.BLACK);
			position = createPositionString(7, i);
			placePiece(pawn, position);
		}
	}
	
	private void createRooks() {
		String position;
		Rook whiteRook1 = new Rook(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 1);
		placePiece(whiteRook1, position);
		
		Rook whiteRook2 = new Rook(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 8);
		placePiece(whiteRook2, position);
		
		Rook blackRook1 = new Rook(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 1);
		placePiece(blackRook1, position);
		
		Rook blackRook2 = new Rook(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 8);
		placePiece(blackRook2, position);
	}
	
	private void createKnights() {
		String position;
		Knight whiteKnight1 = new Knight(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 2);
		placePiece(whiteKnight1, position);
		
		Knight whiteKnight2 = new Knight(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 7);
		placePiece(whiteKnight2, position);
		
		Knight blackKnight1 = new Knight(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 2);
		placePiece(blackKnight1, position);
		
		Knight blackKnight2 = new Knight(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 7);
		placePiece(blackKnight2, position);
	}
	
	private void createBishops() {
		String position;
		Bishop whiteBishop1 = new Bishop(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 3);
		placePiece(whiteBishop1, position);
		
		Bishop whiteBishop2 = new Bishop(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 6);
		placePiece(whiteBishop2, position);
		
		Bishop blackBishop1 = new Bishop(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 3);
		placePiece(blackBishop1, position);
		
		Bishop blackBishop2 = new Bishop(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 6);
		placePiece(blackBishop2, position);

	}
	
	private void createQueens() {
		String position;
		Queen whiteQueen = new Queen(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 4);
		placePiece(whiteQueen, position);
		
		Queen blackQueen = new Queen(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 4);
		placePiece(blackQueen, position);
	}
	
	private void createKings() {
		String position;
		King whiteKing = new King(this, ChessPiece.Color.WHITE);
		position = createPositionString(1, 5);
		placePiece(whiteKing, position);
		
		King blackKing = new King(this, ChessPiece.Color.BLACK);
		position = createPositionString(8, 5);
		placePiece(blackKing, position);
	}
	
	public static ChessBoard initializeBoard(){
		ChessBoard board = new ChessBoard();
		board.initialize();
		return board;
	}

	public String getBoardString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(board);
	}
	
}
