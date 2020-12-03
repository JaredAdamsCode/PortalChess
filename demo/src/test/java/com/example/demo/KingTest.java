package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.example.demo.chessboard.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KingTest {
	
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
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		assertTrue(king.toString().equals("\u2654"));
	}
	
	@Test
	public void testToStringBlack() {
		King king = new King(chessBoard, ChessPiece.Color.BLACK, "King");
		assertTrue(king.toString().equals("\u265A"));
	}
	
	@Test
	public void testNoMoves() throws IllegalPositionException {
		chessBoard.initialize();
		King king = (King) chessBoard.getPiece("e1");
		moves = king.legalMoves();
		assertTrue(moves.size() == 0, "test no moves");
	}
	
	@Test
	public void test8Moves() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(king, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = king.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves");
	}
	
	@Test
	public void test8MovesNoCapture() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		chessBoard.placePiece(king, "d5");
		moves = king.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves no capture");
	}
	
	@Test
	public void test3MovesNoCaptureCorner() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		chessBoard.placePiece(king, "a1");
		moves = king.legalMoves();
		assertTrue(moves.size() == 3, "test 3 moves no capture in corner");
	}
	
	@Test
	public void test7MovesSameColor() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(king, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = king.legalMoves();
		assertTrue(moves.size() == 7, "test 7 moves");
	}
	
	@Test
	public void test8MovesContains() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(king, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = king.legalMoves();
		assertTrue(moves.contains("c6") && moves.contains("d6") && moves.contains("e6")
				&& moves.contains("e5") && moves.contains("e4") && moves.contains("d4")
				&& moves.contains("c4")  && moves.contains("c5"), "test moves contains");
	}
	
	@Test
	public void test8MovesFlipColor() {
		King king = new King(chessBoard, ChessPiece.Color.BLACK, "King");
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(king, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = king.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves flip color");
	}
	
	@Test
	public void testGetColorWhite() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		assertTrue(king.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		King king = new King(chessBoard, ChessPiece.Color.BLACK, "King");
		assertTrue(king.getColor() == ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("e1");
		assertTrue(piece.getPosition().equals("e1"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("e1");
		assertFalse(!piece.getPosition().equals("e1"));
	}

	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		king.setPosition("a8");
		assertEquals("a8", king.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		try {
			king.setPosition("d8X");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		King king = new King(chessBoard, ChessPiece.Color.WHITE, "King");
		try {
			king.setPosition("9a");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}

}
