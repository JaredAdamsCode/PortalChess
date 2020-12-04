package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.chessboard.ChessBoard;
import com.example.demo.chessboard.ChessPiece;
import com.example.demo.chessboard.Queen;
import com.example.demo.chessboard.IllegalPositionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

	private ChessBoard chessBoard;

	@BeforeEach
	public void setUp() {
		chessBoard = new ChessBoard();
	}
	
	@AfterEach
	public void tearDown() {
		chessBoard = null;
	}
	
	@Test
	public void testGetColorWhite() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.WHITE, "Queen");
		assertTrue(Queen.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.BLACK, "Queen");
		assertTrue(Queen.getColor() == ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testWrongColorWhite() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.WHITE, "Queen");
		assertFalse(Queen.getColor() != ChessPiece.Color.WHITE);
	}
	
	@Test
	public void testWrongColorBlack() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.BLACK, "Queen");
		assertFalse(Queen.getColor() != ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("d1");
		assertTrue(piece.getPosition().equals("d1"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("d1");
		assertFalse(!piece.getPosition().equals("d1"));
	}
	
	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.BLACK, "Queen");
		Queen.setPosition("d8");
		assertEquals("d8", Queen.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.BLACK, "Queen");
		try {
			Queen.setPosition("d8X");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		ChessPiece Queen = new Queen(chessBoard, ChessPiece.Color.BLACK, "Queen");
		try {
			Queen.setPosition("d9");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
}
