package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.chessboard.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest {

	private ChessBoard chessBoard;
	private ArrayList<String> moves;

	@BeforeEach
	public void setUp() {
		chessBoard = new ChessBoard();
	}
	
	@AfterEach
	public void tearDown() {
		chessBoard = null;
	}

	@Test
	void testNoMoves() throws IllegalPositionException {
		chessBoard.initialize();
		Knight knight = (Knight) chessBoard.getPiece("b1");
		Pawn pawn1 = (Pawn) chessBoard.getPiece("a2");
		Pawn pawn2 = (Pawn) chessBoard.getPiece("b2");
		Pawn pawn3 = (Pawn) chessBoard.getPiece("c2");
		chessBoard.placePiece(pawn1, "d7");
		chessBoard.placePiece(pawn2, "c6");
		chessBoard.placePiece(pawn3, "a6");
		chessBoard.placePiece(knight, "b8");
		moves = knight.legalMoves();
		assertTrue(moves.size() == 0, "test no moves");
	}

	@Test
	void testValidMoves() throws IllegalPositionException {
		chessBoard.initialize();
		Knight knight = (Knight) chessBoard.getPiece("b1");
		moves = knight.legalMoves();
		ArrayList<String> valueArray = new ArrayList<>(Arrays.asList("c3", "a3"));
		assertEquals(valueArray, moves);
	}

	@Test
	public void testToStringWhite() {
		Knight knight = new Knight(chessBoard, ChessPiece.Color.WHITE, "Knight");
		assertTrue(knight.toString().equals("\u2658"));
	}
	
	@Test
	public void testToStringBlack() {
		Knight knight = new Knight(chessBoard, ChessPiece.Color.BLACK, "Knight");
		assertTrue(knight.toString().equals("\u265E"));
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("b1");
		assertTrue(piece.getPosition().equals("b1"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("b8");
		assertFalse(!piece.getPosition().equals("b8"));
	}

	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		ChessPiece knight = new Knight(chessBoard, ChessPiece.Color.BLACK, "Knight");
		knight.setPosition("g8");
		assertEquals("g8", knight.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		ChessPiece knight = new Knight(chessBoard, ChessPiece.Color.BLACK, "Knight");
		try {
			knight.setPosition("d8X");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		ChessPiece knight = new Knight(chessBoard, ChessPiece.Color.BLACK, "Knight");
		try {
			knight.setPosition("r6");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
}
