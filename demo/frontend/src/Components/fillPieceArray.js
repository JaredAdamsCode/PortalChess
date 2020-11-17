import React from "react";
import { ReactComponent as WhitePawn } from '../Icons/white_pawn.svg';
import { ReactComponent as BlackPawn } from '../Icons/black_pawn.svg';
import { ReactComponent as WhiteKing } from '../Icons/white_king.svg';
import { ReactComponent as BlackKing } from '../Icons/black_king.svg';
import { ReactComponent as WhiteQueen } from '../Icons/white_queen.svg';
import { ReactComponent as BlackQueen } from '../Icons/black_queen.svg';
import { ReactComponent as WhiteRook } from '../Icons/white_rook.svg';
import { ReactComponent as BlackRook } from '../Icons/black_rook.svg';
import { ReactComponent as WhiteKnight } from '../Icons/white_knight.svg';
import { ReactComponent as BlackKnight } from '../Icons/black_knight.svg';
import { ReactComponent as WhiteBishop } from '../Icons/white_bishop.svg';
import { ReactComponent as BlackBishop } from '../Icons/black_bishop.svg';


export default function fillPieceArray(response){

    function getPiece(str) {
        if(str === "White King"){
            return <WhiteKing/>
        }
        if(str === "White Queen"){
            return <WhiteQueen/>
        }
        if(str === "White Pawn"){
            return <WhitePawn/>
        }
        if(str === "White Rook"){
            return <WhiteRook/>
        }
        if(str === "White Knight"){
            return <WhiteKnight/>
        }
        if(str === "White Bishop"){
            return <WhiteBishop/>
        }
        if(str === "Black King"){
            return <BlackKing/>
        }
        if(str === "Black Queen"){
            return <BlackQueen/>
        }
        if(str === "Black Pawn"){
            return <BlackPawn/>
        }
        if(str === "Black Rook"){
            return <BlackRook/>
        }
        if(str === "Black Knight"){
            return <BlackKnight/>
        }
        if(str === "Black Bishop"){
            return <BlackBishop/>
        }
        else{
            return null;
        }
    }

    const arr = new Array(64).fill(null);
    let index = 0;
    for(let i = 0; i < 8; i++){
        for(let j = 0; j < 8; j++ ){
            arr[index] = getPiece(response[i][j]);
            index++;
        }
    }

    return arr;

}