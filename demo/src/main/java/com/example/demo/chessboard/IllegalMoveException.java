package com.example.demo.chessboard;

import java.lang.Exception;

public class IllegalMoveException extends Exception {

	
	public IllegalMoveException(String errorMessage) {
		super(errorMessage);
	}
}
