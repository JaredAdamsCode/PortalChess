package com.example.demo;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message){
        super(message);
    }
}
