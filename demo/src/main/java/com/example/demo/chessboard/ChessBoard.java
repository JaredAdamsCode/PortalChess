package com.example.demo.chessboard;

import com.example.demo.MoveAnalyzer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

public class ChessBoard {
	
	private ChessPiece[][] board;
	
	private int castlingMoves = 0;
	
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
		createPortals();
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

	public String getPiecePosition(String type, ChessPiece.Color color) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if(piece != null && piece.getType().equals(type) && piece.getColor().equals(color)) {
					return piece.getPosition();
				}
			}
		}
		return null;
	}

	protected String getOppositePortalPosition(ChessPiece.Color color) {
		if (color == ChessPiece.Color.WHITE) {
			return getPiecePosition("Portal", ChessPiece.Color.BLACK);
		}else {
			return getPiecePosition("Portal", ChessPiece.Color.WHITE);
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

			// Castling
			if(checkForCastling(fromPiece, toPiece, toPosition)) {
				castle(fromPiece, toPiece, toPosition);
				setCastlingMoves(updateCastlingMoves(fromPosition));
				return;
			}

			legalMoves = fromPiece.legalMoves();

			// Teleport pieces by moving portals onto them
			if (legalMoves.contains(toPosition) && toPiece != null && !(toPiece instanceof Portal)
					&& fromPiece instanceof Portal) {
				String otherPortalLocation = getOppositePortalPosition(fromPiece.getColor());
				int otherPortalRow = Character.getNumericValue(otherPortalLocation.charAt(1));
				int otherPortalColumn = otherPortalLocation.charAt(0) - 96;

				String newPosition = findFreeSpace(otherPortalRow, otherPortalColumn);

				placePiece(toPiece, newPosition); // Teleport piece
				board[toRow - 1][toColumn - 1] = null;

				placePiece(fromPiece, toPosition); // Move portal
				board[fromRow - 1][fromColumn - 1] = null;
				return;
			}

			if(legalMoves.contains(toPosition) &&
					( toPiece == null || toPiece.color != fromPiece.color) || fromPiece instanceof Portal ) {
				// Create black hole by moving one portal onto another
				if(toPiece instanceof Portal && fromPiece instanceof Portal) {
					Portal black_hole = new Portal(this, ChessPiece.Color.BLACK,
							"Portal", Portal.Status.BLACK_HOLE);
					board[toRow - 1][toColumn - 1] = null;
					placePiece(black_hole, toPosition);
					blackHoleFunction(toPosition);
				}

				// Regular Move
				else {
					if (fromPiece instanceof Pawn) {
						fromPiece = pawnPromotion(fromPiece, toRow);
					}
					if (!isNearBlackHole(toPosition)) {
						placePiece(fromPiece, toPosition);
					}
				}
				if(!(fromPiece instanceof Portal && ((Portal) fromPiece).getStatus() == Portal.Status.BLACK_HOLE)) {
					board[fromRow - 1][fromColumn - 1] = null;
				}else {
					throw new IllegalMoveException("Cannot move black hole");
				}
				
				if(fromPiece instanceof King || fromPiece instanceof Rook) {
					setCastlingMoves(updateCastlingMoves(fromPosition));
				}
			}else {
				throw new IllegalMoveException("Cannot make move from " + fromPosition + " to " + toPosition);
			}
		} catch (IllegalPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private boolean isNearBlackHole(String toPosition) {
		int toRow = Character.getNumericValue(toPosition.charAt(1));
		int toColumn = toPosition.charAt(0) - 96;

		Vector<String> surroundingSquares = getValidSurroundingSquares(toRow, toColumn);

		for(String pos : surroundingSquares) {
			try {
				boolean isPortal = getPiece(pos) instanceof Portal;
				if (isPortal && ((Portal) getPiece(pos)).getStatus() == Portal.Status.BLACK_HOLE) {
					return true;
				}
			} catch(IllegalPositionException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private String[] getSurroundingSquares(int row, int col) {
		String upperLeft = createValidPositionString(row + 1, col - 1);
		String up = createValidPositionString(row + 1, col);
		String upperRight = createValidPositionString(row + 1, col + 1);

		String left = createValidPositionString(row, col - 1);
		String right = createValidPositionString(row, col + 1);

		String lowerLeft = createValidPositionString(row - 1, col - 1);
		String down = createValidPositionString(row - 1, col);
		String lowerRight = createValidPositionString(row - 1, col + 1);

		String[] squares = {upperLeft, up, upperRight, left, right, lowerLeft, down, lowerRight};
		return squares;
	}

	private Vector<String> getValidSurroundingSquares(int row, int col) {
		Vector<String> validSquares = new Vector<String>();
		String[] potentialSquares = getSurroundingSquares(row, col);
		for(String pos : potentialSquares) {
			if(pos != "Invalid") {
				validSquares.add(pos);
			}
		}
		return validSquares;
	}

	private String findFreeSpace(int row, int col) {
		Vector<String> squares = getValidSurroundingSquares(row, col);
		for(String position : squares) {
			try {
				if (getPiece(position) == null) {
					return position;
				}
			} catch (IllegalPositionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void blackHoleFunction(String position) {
		int toRow = Character.getNumericValue(position.charAt(1)) - 1;
		int toColumn = position.charAt(0) - 97;

		nullifySquare(toRow + 1, toColumn - 1);
		nullifySquare(toRow + 1, toColumn);
		nullifySquare(toRow + 1, toColumn + 1);

		nullifySquare(toRow, toColumn - 1);
		nullifySquare(toRow, toColumn + 1);

		nullifySquare(toRow - 1, toColumn - 1);
		nullifySquare(toRow - 1, toColumn);
		nullifySquare(toRow - 1, toColumn + 1);
	}

	private void nullifySquare(int row, int col) {
		if(row <= 8 && row >= 1 && col <= 8 && col >= 1) {
			board[row][col] = null;
		}
	}
	
	public ChessPiece pawnPromotion(ChessPiece fromPiece, int toRow) {
		if(fromPiece.color.equals(ChessPiece.Color.WHITE)) {
			if(toRow == 8) {
				return new Queen(this, ChessPiece.Color.WHITE, "Queen");
			}
		}
		else {
			if(toRow == 1) {
				return new Queen(this, ChessPiece.Color.BLACK, "Queen");
			}
		}
		return fromPiece;
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

	private String createValidPositionString(int row, int col) {
		if(row <= 8 && row >= 1 && col <= 8 && col >= 1) {
			return createPositionString(row, col);
		}
		return "Invalid";
	}
	
	private void createPawns() {
		// create WHITE pawns
		String position;
		for(int i = 1; i <= 8; i++) {
			Pawn pawn = new Pawn(this, ChessPiece.Color.WHITE, "Pawn");
			position = createPositionString(2, i);
			placePiece(pawn, position);
		}
		// create BLACK pawns
		for(int i = 1; i <= 8; i++) {
			Pawn pawn = new Pawn(this, ChessPiece.Color.BLACK, "Pawn");
			position = createPositionString(7, i);
			placePiece(pawn, position);
		}
	}
	
	private void createRooks() {
		String position;
		Rook whiteRook1 = new Rook(this, ChessPiece.Color.WHITE, "Rook");
		position = createPositionString(1, 1);
		placePiece(whiteRook1, position);
		
		Rook whiteRook2 = new Rook(this, ChessPiece.Color.WHITE, "Rook");
		position = createPositionString(1, 8);
		placePiece(whiteRook2, position);
		
		Rook blackRook1 = new Rook(this, ChessPiece.Color.BLACK, "Rook");
		position = createPositionString(8, 1);
		placePiece(blackRook1, position);
		
		Rook blackRook2 = new Rook(this, ChessPiece.Color.BLACK, "Rook");
		position = createPositionString(8, 8);
		placePiece(blackRook2, position);
	}
	
	private void createKnights() {
		String position;
		Knight whiteKnight1 = new Knight(this, ChessPiece.Color.WHITE, "Knight");
		position = createPositionString(1, 2);
		placePiece(whiteKnight1, position);
		
		Knight whiteKnight2 = new Knight(this, ChessPiece.Color.WHITE, "Knight");
		position = createPositionString(1, 7);
		placePiece(whiteKnight2, position);
		
		Knight blackKnight1 = new Knight(this, ChessPiece.Color.BLACK, "Knight");
		position = createPositionString(8, 2);
		placePiece(blackKnight1, position);
		
		Knight blackKnight2 = new Knight(this, ChessPiece.Color.BLACK, "Knight");
		position = createPositionString(8, 7);
		placePiece(blackKnight2, position);
	}
	
	private void createBishops() {
		String position;
		Bishop whiteBishop1 = new Bishop(this, ChessPiece.Color.WHITE, "Bishop");
		position = createPositionString(1, 3);
		placePiece(whiteBishop1, position);
		
		Bishop whiteBishop2 = new Bishop(this, ChessPiece.Color.WHITE, "Bishop");
		position = createPositionString(1, 6);
		placePiece(whiteBishop2, position);
		
		Bishop blackBishop1 = new Bishop(this, ChessPiece.Color.BLACK, "Bishop");
		position = createPositionString(8, 3);
		placePiece(blackBishop1, position);
		
		Bishop blackBishop2 = new Bishop(this, ChessPiece.Color.BLACK, "Bishop");
		position = createPositionString(8, 6);
		placePiece(blackBishop2, position);

	}
	
	private void createQueens() {
		String position;
		Queen whiteQueen = new Queen(this, ChessPiece.Color.WHITE, "Queen");
		position = createPositionString(1, 4);
		placePiece(whiteQueen, position);
		
		Queen blackQueen = new Queen(this, ChessPiece.Color.BLACK, "Queen");
		position = createPositionString(8, 4);
		placePiece(blackQueen, position);
	}
	
	private void createKings() {
		String position;
		King whiteKing = new King(this, ChessPiece.Color.WHITE, "King");
		position = createPositionString(1, 5);
		placePiece(whiteKing, position);
		
		King blackKing = new King(this, ChessPiece.Color.BLACK, "King");
		position = createPositionString(8, 5);
		placePiece(blackKing, position);
	}

	private void createPortals() {
		String position;
		Portal whitePortal = new Portal(this, ChessPiece.Color.WHITE,
				"Portal", Portal.Status.PORTAL);
		position = createPositionString(4, 2);
		placePiece(whitePortal, position);

		Portal blackPortal = new Portal(this, ChessPiece.Color.BLACK,
				"Portal", Portal.Status.PORTAL);
		position = createPositionString(5, 7);
		placePiece(blackPortal, position);
	}
	
	public static ChessBoard initializeBoard(){
		ChessBoard board = new ChessBoard();
		board.initialize();
		return board;
	}

	public String getBoardString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this.board);
	}

	public ChessPiece[][] getBoard(){
		return this.board;
	}

	public void createChessPieceObject(JSONObject obj, ChessBoard tempBoard) throws JSONException {

		String color = obj.getString("color");
		String position = obj.getString("position");
		String type = obj.getString("type");

		ChessPiece piece;
		ChessPiece.Color pieceColor;

		if (color.equals("WHITE")) {
			pieceColor = ChessPiece.Color.WHITE;
		} else {
			pieceColor = ChessPiece.Color.BLACK;
		}

		switch (type) {
			case "Portal":
				Portal.Status status = Portal.Status.valueOf(obj.getString("status"));
				piece = new Portal(tempBoard, pieceColor, "Portal", status);
				break;
			case "Rook":
				piece = new Rook(tempBoard, pieceColor, "Rook");
				break;
			case "Knight":
				piece = new Knight(tempBoard, pieceColor, "Knight");
				break;
			case "Bishop":
				piece = new Bishop(tempBoard, pieceColor, "Bishop");
				break;
			case "Queen":
				piece = new Queen(tempBoard, pieceColor, "Queen");
				break;
			case "King":
				piece = new King(tempBoard, pieceColor, "King");
				break;
			case "Pawn":
				piece = new Pawn(tempBoard, pieceColor, "Pawn");
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + type);
		}

		placePiece(piece,position);

	}
	
	public void setCastlingMoves(int cMoves) {
		this.castlingMoves = cMoves;
	}
	
	public int getCastlingMoves() {
		return this.castlingMoves;
	}
	
	public int updateCastlingMoves(String fromPosition) {
		if(fromPosition.equals("a1")) {
			return this.castlingMoves | 4;
		}
		if(fromPosition.equals("h1")) {
			return this.castlingMoves | 1;
		}
		if(fromPosition.equals("e1")) {
			return this.castlingMoves | 2;
		}
		if(fromPosition.equals("a8")) {
			return this.castlingMoves | 32;
		}
		if(fromPosition.equals("h8")) {
			return this.castlingMoves | 8;
		}
		if(fromPosition.equals("e8")) {
			return this.castlingMoves | 16;
		}
		return this.castlingMoves;
	}
	
	private boolean checkForCastling(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) {
		
		if(!(fromPiece instanceof King)) return false;
		if(fromPiece.color.equals(ChessPiece.Color.WHITE)) {
			if(toPosition.equals("c1") || toPosition.equals("g1")) {
				return true;
			}
		}
		else {
			if(toPosition.equals("c8") || toPosition.equals("g8")) {
				return true;
			}
		}
		return false;
	}
	
	private void castle(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) throws IllegalMoveException, IllegalPositionException {
		// check that space King moves to is open
		if(toPiece != null) {
			throw new IllegalMoveException("Cannot castle - space is occupied");
		}
		// check if King is in check
		if(inCheck(fromPiece)) {
			throw new IllegalMoveException("Cannot castle - King is in check");
		}
		// validate castling requirements and castle if legal
		if(fromPiece.getColor().equals(ChessPiece.Color.WHITE)) {
			if(validateWhiteCastle(fromPiece, toPiece, toPosition)) {
				whiteCastle(fromPiece, toPiece, toPosition);
			}
		}
		else {
			if(validateBlackCastle(fromPiece, toPiece, toPosition)) {
				blackCastle(fromPiece, toPiece, toPosition);
			}
		}
		
		
	}
	
	private boolean validateWhiteCastle(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) throws IllegalMoveException, IllegalPositionException {
		// check spaces are unoccupied
		if(toPosition.equals("c1") && this.board[0][3] != null && this.board[0][1] != null) {
			throw new IllegalMoveException("Cannot castle - space is occupied");
		}
		if(toPosition.equals("g1") && this.board[0][5] != null) {
			throw new IllegalMoveException("Cannot castle - space is occupied");
		}
		// check if King and chosen rook have moved
		if((toPosition.equals("c1") && (this.castlingMoves & 6) != 0)) {
			throw new IllegalMoveException("Cannot castle - King and/or rook has moved before");
		}
		if((toPosition.equals("g1") && (this.castlingMoves & 3) != 0)) {
			throw new IllegalMoveException("Cannot castle - King and/or rook has moved before");
		}
		// check that king does not move through check
		ArrayList<String> attacked = attackedSpaces(fromPiece.getColor());
		if(toPosition.equals("c1") && (attacked.contains("d1"))) {
			throw new IllegalMoveException("Cannot castle - king cannot move through check");
		}
		if(toPosition.equals("g1") && (attacked.contains("f1"))) {
			throw new IllegalMoveException("Cannot castle - king cannot move through check");
		}
		
		return true;
	}
	
	private boolean validateBlackCastle(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) throws IllegalMoveException {
		// check spaces are unoccupied
		if(toPosition.equals("c8") && this.board[7][3] != null && this.board[7][1] != null) {
			throw new IllegalMoveException("Cannot castle - space is occupied");
		}
		if(toPosition.equals("g8") && this.board[7][5] != null) {
			throw new IllegalMoveException("Cannot castle - space is occupied");
		}
		// check if King and chosen rook have moved
		if((toPosition.equals("c8") && (this.castlingMoves & 48) != 0)) {
			throw new IllegalMoveException("Cannot castle - King and/or rook has moved before");
		}
		if((toPosition.equals("g1") && (this.castlingMoves & 24) != 0)) {
			throw new IllegalMoveException("Cannot castle - King and/or rook has moved before");
		}
		// check that king does not move through check
		ArrayList<String> attacked = attackedSpaces(fromPiece.getColor());
		if(toPosition.equals("c8") && (attacked.contains("d8"))) {
			throw new IllegalMoveException("Cannot castle - king cannot move through check");
		}
		if(toPosition.equals("g8") && (attacked.contains("f8"))) {
			throw new IllegalMoveException("Cannot castle - king cannot move through check");
		}
		return true;
	}
	
	private void whiteCastle(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) {
		if(toPosition.equals("c1")) {
			ChessPiece rook = board[0][0];
			board[0][3] = rook;
			board[0][0] = null;
			board[0][2] = fromPiece;
			board[0][4] = null; 
		}
		else {
			ChessPiece rook = board[0][7];
			board[0][5] = rook;
			board[0][7] = null;
			board[0][6] = fromPiece;
			board[0][4] = null; 
		}
	}
	
	private void blackCastle(ChessPiece fromPiece, ChessPiece toPiece, String toPosition) {
		if(toPosition.equals("c8")) {
			ChessPiece rook = board[7][0];
			board[7][3] = rook;
			board[7][0] = null;
			board[7][2] = fromPiece;
			board[7][4] = null; 
		}
		else {
			ChessPiece rook = board[7][7];
			board[7][5] = rook;
			board[7][7] = null;
			board[7][6] = fromPiece;
			board[7][4] = null; 
		}
	}
	
	private ArrayList<String> attackedSpaces(ChessPiece.Color color){
		ArrayList attacked = new ArrayList<String>();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if(piece != null && !piece.getColor().equals(color)) {
					attacked.addAll(piece.legalMoves());
				}
			}
		}
		return attacked;
	}
	
	private boolean inCheck(ChessPiece fromPiece) {
		ArrayList<String> attacked = attackedSpaces(fromPiece.getColor());
		if(attacked.contains(fromPiece.getPosition())) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IllegalMoveException {
		ChessBoard board = new ChessBoard();
		board.initialize();
		System.out.println(board);
//		board.move("a2", "a4");
//		System.out.println(board);
//		board.move("a7", "a5");
//		System.out.println(board);
//		board.move("b2", "b4");
//		System.out.println(board);
//		board.move("a5", "b4");
//		System.out.println(board);
//		board.move("a4", "a5");
//		System.out.println(board);
//		board.move("a8", "a6");
//		System.out.println(board);
//		board.move("a1", "a2");
//		System.out.println(board);
//		board.move("a6", "b6");
//		System.out.println(board);
//		board.move("a5", "a6");
//		System.out.println(board);
//		board.move("b6", "c6");
//		System.out.println(board);
//		board.move("a6", "a7");
//		System.out.println(board);
//		board.move("c6", "d6");
//		System.out.println(board);
//		board.move("a7", "a8");
//		System.out.println(board);
//		board.move("b7", "b5");
//		System.out.println(board);
//		board.move("a4", "b5");
//		System.out.println(board);
//		board.move("a7", "a5");
//		System.out.println(board);
//		board.move("a1", "a5");
//		System.out.println(board);
//		board.move("c7", "c5");
//		System.out.println(board);
//		board.move("a5", "a8");
//		System.out.println(board);
		
		// basic valid white and black castling king side
//		board.move("e2", "e4");
//		System.out.println(board);
//		
//		board.move("e7", "e5");
//		System.out.println(board);
//		
//		board.move("f1", "e2");
//		System.out.println(board);
//		
//		board.move("f8", "e7");
//		System.out.println(board);
//		
//		board.move("g1", "h3");
//		System.out.println(board);
//		
//		board.move("g8", "h6");
//		System.out.println(board);
//		
//		board.move("e1", "g1");
//		System.out.println(board);
//
//		board.move("e8", "g8");
//		System.out.println(board);
//		
//		board.move("b2", "b3");
//		System.out.println(board);
		
		// basic valid white and black castling queen side
//		board.move("d2", "d4");
//		System.out.println(board);
//		
//		board.move("d7", "d5");
//		System.out.println(board);
//		
//		board.move("c1", "e3");
//		System.out.println(board);
//		
//		board.move("c8", "e6");
//		System.out.println(board);
//		
//		board.move("b1", "a3");
//		System.out.println(board);
//		
//		board.move("b8", "a6");
//		System.out.println(board);
//		
//		board.move("d1", "d2");
//		System.out.println(board);
//
//		board.move("d8", "d7");
//		System.out.println(board);
//		
//		board.move("e1", "c1");
//		System.out.println(board);
//
//		board.move("e8", "c8");
//		System.out.println(board);
		
		// invalid castling with queen side rook moved
//		board.move("d2", "d4");
//		System.out.println(board);
//		
//		board.move("d7", "d5");
//		System.out.println(board);
//		
//		board.move("c1", "e3");
//		System.out.println(board);
//		
//		board.move("c8", "e6");
//		System.out.println(board);
//		
//		board.move("b1", "a3");
//		System.out.println(board);
//		
//		board.move("b8", "a6");
//		System.out.println(board);
//		
//		board.move("d1", "d2");
//		System.out.println(board);
//
//		board.move("d8", "d7");
//		System.out.println(board);
//		
//		
//		board.move("a1", "b1");
//		System.out.println(board);
//
//		board.move("a8", "b8");
//		System.out.println(board);
//		
//		
//		
//		
//		
//		board.move("e1", "c1");
//		System.out.println(board);
//
//		board.move("e8", "c8");
//		System.out.println(board);
		
		
	}
}
