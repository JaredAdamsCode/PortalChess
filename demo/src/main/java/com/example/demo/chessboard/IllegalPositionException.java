package com.example.demo.chessboard;

import java.lang.Exception;

public class IllegalPositionException extends Exception{
	
	
	public IllegalPositionException(String errorMessage) {
		super(errorMessage);
	}

}
