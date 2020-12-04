package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void createMoveTest(){
        Move move = new Move("a3","c4",1,2);
        assertNotEquals(null, move);
    }

    @Test
    public void createEmptyMoveTest(){
        Move move = new Move();
        assertNotEquals(null, move);
    }

    @Test
    public void fromPositionTest(){
        Move move = new Move("a3","c4",1,2);
        assertEquals("a3", move.getFromPosition());
    }

    @Test
    public void matchIdTest(){
        Move move = new Move("a3","c4",1,2);
        assertEquals(1, move.getMatchId());
    }

    @Test
    public void playerIdTest(){
        Move move = new Move("a3","c4",1,2);
        assertEquals(2, move.getPlayerId());
    }
}
