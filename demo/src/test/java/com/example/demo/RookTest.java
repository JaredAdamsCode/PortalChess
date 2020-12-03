package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.example.demo.chessboard.ChessBoard;
import com.example.demo.chessboard.ChessPiece;
import com.example.demo.chessboard.IllegalPositionException;
import com.example.demo.chessboard.Rook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {

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
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		assertTrue(rook.toString().equals("\u2656"));
	}
	
	@Test
	public void testToStringBlack() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		assertTrue(rook.toString().equals("\u265C"));
	}
	
	@Test
	public void testNoMoves() throws IllegalPositionException {
		chessBoard.initialize();
		Rook rook = (Rook) chessBoard.getPiece("a1");
		moves = rook.legalMoves();
		assertTrue(moves.size() == 0, "test no moves");
	}
	
	@Test
	public void test14Moves() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(rook, "a1");
		chessBoard.placePiece(rook2, "a8");
		moves = rook.legalMoves();
		assertTrue(moves.size() == 14, "test 14 moves");
	}
	
	@Test
	public void test14MovesContains() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(rook, "a1");
		chessBoard.placePiece(rook2, "a8");
		moves = rook.legalMoves();
		assertTrue(moves.contains("a5"), "test moves contains a5");
	}
	
	@Test
	public void test14MovesFlipColor() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(rook, "a1");
		chessBoard.placePiece(rook2, "a8");
		moves = rook.legalMoves();
		assertTrue(moves.size() == 14, "test 14 moves");
	}
	
	@Test
	public void test13MovesWithCapture() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(rook, "a1");
		chessBoard.placePiece(rook2, "a7");
		moves = rook.legalMoves();
		assertTrue(moves.size() == 13, "test 13 moves");
	}
	
	@Test
	public void test13MovesWithSameColor() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook rook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(rook, "a1");
		chessBoard.placePiece(rook2, "a8");
		moves = rook.legalMoves();
		assertTrue(moves.size() == 13, "test 13 moves");
	}
	
	@Test
	public void testGetColorWhite() {
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		assertTrue(rook.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		ChessPiece rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		assertTrue(rook.getColor() == ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("a1");
		assertTrue(piece.getPosition().equals("a1"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("a1");
		assertFalse(!piece.getPosition().equals("a1"));
	}

	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		ChessPiece rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		rook.setPosition("a8");
		assertEquals("a8", rook.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		ChessPiece rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		try {
			rook.setPosition("d8X");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		ChessPiece rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		try {
			rook.setPosition("a0");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
}
