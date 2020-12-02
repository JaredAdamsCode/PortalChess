package com.example.demo;


import com.example.demo.chessboard.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PortalTest {
	
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
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		assertTrue(portal.toString().equals("\u25ce"));
	}

	@Test
	public void testToStringWhiteBH() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.BLACK_HOLE);
		assertTrue(portal.toString().equals("\u25c9"));
	}

	@Test
	public void testToStringBlack() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.BLACK, "Portal", Portal.Status.PORTAL);
		assertTrue(portal.toString().equals("\u25a2"));
	}

	@Test
	public void testToStringBlackBH() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.BLACK, "Portal", Portal.Status.BLACK_HOLE);
		assertTrue(portal.toString().equals("\u25a3"));
	}

	@Test
	public void testStatus() throws IllegalPositionException {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.BLACK, "Portal", Portal.Status.PORTAL);
		assertTrue(portal.getStatus().equals(Portal.Status.PORTAL));
		portal.setStatus(Portal.Status.BLACK_HOLE);
		assertTrue(portal.getStatus().equals(Portal.Status.BLACK_HOLE));
	}
	
	@Test
	public void test8Moves() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(portal, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves");
	}

	@Test
	public void testBHMoves() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.BLACK_HOLE);
		chessBoard.placePiece(portal, "d5");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 0, "test 8 moves");
	}
	
	@Test
	public void test8MovesNoCapture() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		chessBoard.placePiece(portal, "d5");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves no capture");
	}
	
	@Test
	public void test3MovesNoCaptureCorner() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		chessBoard.placePiece(portal, "a1");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 3, "test 3 moves no capture in corner");
	}
	
	@Test
	public void test8MovesSameColor() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(portal, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves");
	}
	
	@Test
	public void test8MovesContains() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK, "Rook");
		chessBoard.placePiece(portal, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = portal.legalMoves();
		assertTrue(moves.contains("c6") && moves.contains("d6") && moves.contains("e6")
				&& moves.contains("e5") && moves.contains("e4") && moves.contains("d4")
				&& moves.contains("c4")  && moves.contains("c5"), "test moves contains");
	}
	
	@Test
	public void test8MovesFlipColor() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE, "Rook");
		chessBoard.placePiece(portal, "d5");
		chessBoard.placePiece(rook, "c6");
		moves = portal.legalMoves();
		assertTrue(moves.size() == 8, "test 8 moves flip color");
	}
	
	@Test
	public void testGetColorWhite() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		assertTrue(portal.getColor() == ChessPiece.Color.WHITE);
	}

	@Test
	public void testGetColorBlack() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.BLACK, "Portal", Portal.Status.PORTAL);
		assertTrue(portal.getColor() == ChessPiece.Color.BLACK);
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
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		portal.setPosition("a8");
		assertEquals("a8", portal.getPosition());
	}
	
	@Test
	public void testSetPositionInvalidPosition() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		try {
			portal.setPosition("d8X");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}
	
	@Test
	public void testSetPositionOffBoard() {
		Portal portal = new Portal(chessBoard, ChessPiece.Color.WHITE, "Portal", Portal.Status.PORTAL);
		try {
			portal.setPosition("9a");
		} catch (IllegalPositionException e) {
			return;
		}
		fail("Illegal Position Exception expected");
	}

}
