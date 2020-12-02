package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.chessboard.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

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
	public void testPreInitialize() throws IllegalPositionException {
		assertNull(chessBoard.getPiece("d1"));
	}
	
	@Test
	public void testInitializeWhiteQueen() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece testWhiteQueen = chessBoard.getPiece("d1");
		assertTrue(testWhiteQueen instanceof Queen, "Test queen");
	}
	
	@Test
	public void testInitializeBlackQueen() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece testQueen = chessBoard.getPiece("d8");
		assertTrue(testQueen instanceof Queen, "Test queen");
	}
	
	@Test
	public void testInitializeEmptySpace() throws IllegalPositionException {
		chessBoard.initialize();
		assertNull(chessBoard.getPiece("d3"));
	}
	
	@Test 
	public void testGetPieceValidPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece pawn = chessBoard.getPiece("c2");
		assertTrue(pawn instanceof Pawn, "Test valid positoin for pawn");
	}
	
	@Test 
	public void testGetPieceInvalidPosition() {
		try {
			chessBoard.getPiece("Invalid");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception Expected");
	}
	
	@Test
	public void testPlacePieceInvalidCharacters() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		assertFalse(chessBoard.placePiece(pawn, "e3X"), "Invalid placement expected");
	}
	
	@Test
	public void testPlacePieceOffBoard() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		assertFalse(chessBoard.placePiece(pawn, "e9"), "Invalid placement - off board");
	}
	
	@Test
	public void testPlacePieceSameColor() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		Pawn pawn2 = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(pawn, "c2");
		assertFalse(chessBoard.placePiece(pawn2, "c2"), "Invalid placement - same color");
	}
	
	@Test
	public void testPlacePieceValid() {
		Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		Pawn pawn2 = new Pawn(chessBoard, ChessPiece.Color.WHITE, "Pawn");
		chessBoard.placePiece(pawn, "c2");
		assertTrue(chessBoard.placePiece(pawn2, "d2"), "Valid Placement");
	}
	
	@Test
	public void testMoveToNull() throws IllegalMoveException, IllegalPositionException {
		chessBoard.initialize();
		chessBoard.move("c2", "c4");
		assertTrue(chessBoard.getPiece("c2") == null && chessBoard.getPiece("c4") instanceof Pawn, "Test move to empty space");
	}

	@Test
	public void testMoveToCapture() throws IllegalMoveException, IllegalPositionException {
		Rook whiteRook1 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook blackRook1 = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(whiteRook1, "d3");
		chessBoard.placePiece(blackRook1, "d6");
		chessBoard.move("d3", "d6");
		assertTrue(chessBoard.getPiece("d3") == null && chessBoard.getPiece("d6").equals(whiteRook1), "Test move to capture");
	}
	
	@Test
	public void testMoveToSameColor() {
		Rook whiteRook1 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		Rook whiteRook2 = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(whiteRook1, "b1");
		chessBoard.placePiece(whiteRook2, "b8");
		try {
			chessBoard.move("b1", "b8");
		} catch (IllegalMoveException e) {
			return;
		}
		fail("Illegal Move Exception expected");
	}

}
