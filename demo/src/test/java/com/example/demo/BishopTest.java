package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.example.demo.chessboard.Bishop;
import com.example.demo.chessboard.ChessBoard;
import com.example.demo.chessboard.ChessPiece;
import com.example.demo.chessboard.IllegalPositionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopTest {

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
	void noMoves() throws IllegalPositionException {
		chessBoard.initialize();
		Bishop bishop = (Bishop) chessBoard.getPiece("c1");
		moves = bishop.legalMoves();
		assertTrue(moves.size() == 0, "test no moves");	
	}
	
	@Test
	public void test13Moves() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "a2");
		moves = bishop.legalMoves();
		assertTrue(moves.size() == 13, "test 13 moves");
	}
	
	@Test
	public void test13MovesFlipColor() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "a2");
		moves = bishop.legalMoves();
		assertTrue(moves.size() == 13, "test 13 moves");
	}
	
	@Test
	public void test13MovesSameColor() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "a2");
		moves = bishop.legalMoves();
		assertTrue(moves.size() == 12, "test 13 moves - same color");
	}
	
	@Test
	public void test11MovesSameColor() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "b3");
		moves = bishop.legalMoves();
		assertTrue(moves.size() == 11, "test 13 moves - same color");
	}
	
	@Test
	public void test4Moves() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "a2");
		moves = bishop2.legalMoves();
		assertTrue(moves.size() == 4, "test 4 moves");
	}
	
	@Test
	public void test14MovesContains() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		chessBoard.placePiece(bishop, "d5");
		chessBoard.placePiece(bishop2, "a2");
		moves = bishop.legalMoves();
		assertTrue(moves.contains("a2"), "test moves contains a5");
	}
	
	@Test
	public void testGetColorWhite() {
		Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE, "Bishop");
		assertTrue(bishop.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		assertTrue(bishop2.getColor() == ChessPiece.Color.BLACK);
	}
	
	@Test
	public void testGetPosition() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("c1");
		assertTrue(piece.getPosition().equals("c1"));
	}
	
	@Test
	public void testGetPositionFalse() throws IllegalPositionException {
		chessBoard.initialize();
		ChessPiece piece = chessBoard.getPiece("c1");
		assertFalse(!piece.getPosition().equals("c1"));
	}

	@Test
	public void testSetPositionValid() throws IllegalPositionException {
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		bishop2.setPosition("a8");
		assertEquals("a8", bishop2.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		try {
			bishop2.setPosition("a");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		Bishop bishop2 = new Bishop(chessBoard, ChessPiece.Color.BLACK, "Bishop");
		try {
			bishop2.setPosition("c0");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	
}
