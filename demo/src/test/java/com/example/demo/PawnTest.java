package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.example.demo.chessboard.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTest {

	private ChessBoard chessBoard;
	private ArrayList<String> moves;

	@BeforeEach
	void setUp() throws Exception {
		chessBoard = new ChessBoard();
	}

	@AfterEach
	void tearDown() throws Exception {
		chessBoard = null;
	}
	
	@Test
	public void testToStringWhite() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		assertTrue(pawn.toString().equals("\u2659"));
	}
	
	@Test
	public void testToStringBlack() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		assertTrue(pawn.toString().equals("\u265F"));
	}
	
	@Test
	public void testInitialMovesWhite() throws IllegalPositionException {
		chessBoard.initialize();
		Pawn pawn = (Pawn) chessBoard.getPiece("h2");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 2, "test initial moves");
	}
	
	@Test
	public void testInitial3MovesWhite() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d3");
		chessBoard.placePiece(pawn, "c2");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 3, "test initial moves 3 possibilities");
	}
	
	@Test
	public void testInitial4MovesWhite() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d3");
		chessBoard.placePiece(rook2, "b3");
		chessBoard.placePiece(pawn, "c2");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 4, "test initial moves 4 possibilities");
	}
	
	@Test
	public void testNonInitialMovesWhite() throws IllegalPositionException {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(pawn, "g3");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 1, "test non-initial moves");
	}
	
	@Test
	public void testNoMovesWhite() throws IllegalPositionException {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(pawn, "g8");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 0, "test no moves");
	}
	
	@Test
	public void testNonInitial2MovesWhite() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d4");
		chessBoard.placePiece(pawn, "c3");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 2, "test non-initial moves 2 possibilities");
	}
	
	@Test
	public void testNonInitial1MovesWhiteSameColorAtAttack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d4");
		chessBoard.placePiece(pawn, "c3");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 1, "test non-initial moves 1 possibilities same color at attack");
	}
	
	@Test
	public void testNonInitial3MovesWhite() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d4");
		chessBoard.placePiece(pawn, "c3");
		chessBoard.placePiece(rook2, "b4");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 3, "test non-initial moves 3 possibilities");
	}
	
	@Test
	public void testNonInitial3MovesWhiteContains() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(rook, "d4");
		chessBoard.placePiece(pawn, "c3");
		chessBoard.placePiece(rook2, "b4");
		moves = pawn.legalMoves();
		assertTrue(moves.contains("d4") && moves.contains("b4") && moves.contains("c4"), "test non-initial moves 3 possibilities");
	}
	
	@Test
	public void testInitialMovesBlack() throws IllegalPositionException {
		chessBoard.initialize();
		Pawn pawn = (Pawn) chessBoard.getPiece("h7");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 2, "test initial moves");
	}
	
	@Test
	public void testInitial3MovesBlack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d6");
		chessBoard.placePiece(pawn, "c7");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 3, "test initial moves 3 possibilities");
	}
	
	@Test
	public void testInitial4MovesBlack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d6");
		chessBoard.placePiece(rook2, "b6");
		chessBoard.placePiece(pawn, "c7");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 4, "test initial moves 4 possibilities");
	}
	
	@Test
	public void testNonInitialMovesBlack() throws IllegalPositionException {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(pawn, "g6");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 1, "test non-initial moves");
	}
	
	@Test
	public void testNonInitial2MovesBlack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d5");
		chessBoard.placePiece(pawn, "c6");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 2, "test non-initial moves 2 possibilities");
	}
	
	@Test
	public void testNonInitialNoMovesBlockBlack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "c5");
		chessBoard.placePiece(pawn, "c6");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 0, "test non-initial moves 2 possibilities");
	}
	
	@Test
	public void testNonInitial1MovesBlackSameColorAtAttack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d5");
		chessBoard.placePiece(pawn, "c6");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 1, "test non-initial moves 1 possibilities same color at attack");
	}
	
	@Test
	public void testNonInitial3MovesBlack() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d5");
		chessBoard.placePiece(pawn, "c6");
		chessBoard.placePiece(rook2, "b5");
		moves = pawn.legalMoves();
		assertTrue(moves.size() == 3, "test non-initial moves 3 possibilities");
	}
	
	@Test
	public void testNonInitial3MovesBlackContains() throws IllegalPositionException {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		chessBoard.placePiece(rook, "d5");
		chessBoard.placePiece(pawn, "c6");
		chessBoard.placePiece(rook2, "b5");
		moves = pawn.legalMoves();
		assertTrue(moves.contains("d5") && moves.contains("c5") && moves.contains("b5"), "test non-initial moves 3 possibilities");
	}
	
	@Test
	public void testGetColorWhite() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		assertTrue(pawn.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		assertTrue(pawn.getColor() == ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("a2");
		assertTrue(piece.getPosition().equals("a2"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("a2");
		assertFalse(!piece.getPosition().equals("a2"));
	}

	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		ChessPiece pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		pawn.setPosition("a8");
		assertEquals("a8", pawn.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		ChessPiece pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		try {
			pawn.setPosition("f2x");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		ChessPiece pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK, "Pawn");
		try {
			pawn.setPosition("a0");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}

}
