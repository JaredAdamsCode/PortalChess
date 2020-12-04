package com.example.demo;

import com.example.demo.chessboard.ChessBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
    Match match = new Match();

    @Test
    public void testId(){
        match.setId(1);
        assertEquals(1, match.getId());
    }

    @Test
    public void testSenderId(){
        match.setSenderID(2);
        assertEquals(2, match.getSenderID());
    }

    @Test
    public void testReceiverId(){
        match.setReceiverID(3);
        assertEquals(3, match.getReceiverID());
    }

    @Test
    public void testStatus(){
        match.setStatus("busy");
        assertEquals("busy", match.getStatus());
    }

    @Test
    public void testWinner(){
        match.setWinner(2);
        assertEquals(2, match.getWinner());
    }

    @Test
    public void testLoser(){
        match.setLoser(3);
        assertEquals(3, match.getLoser());
    }

    @Test
    public void testBoard(){
        ChessBoard chessBoard = new ChessBoard();
        match.setBoard(chessBoard.toString());
        assertEquals(chessBoard.toString(), match.getBoard());
    }
}
