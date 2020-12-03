package com.example.demo;

public class MoveResponse{

    private String moveWasMade;
    private String errorMsg;

    public MoveResponse(String moveWasMade, String errorMsg){
        this.moveWasMade = moveWasMade;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString(){
        return "MoveResponse [moveWasMade= " + moveWasMade + ", errorMsg=" + errorMsg + "]";
    }
}