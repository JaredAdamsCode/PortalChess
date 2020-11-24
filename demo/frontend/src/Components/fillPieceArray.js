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
import { ReactComponent as WhitePortal } from '../Icons/white_portal.svg';
import { ReactComponent as BlackPortal } from '../Icons/black_portal.svg';


export default function fillPieceArray(response){

    function getPiece(piece) {
        if(piece){
            if(piece.type === "King"){
                if(piece.color === "WHITE"){
                    return <WhiteKing/>
                }
                else{
                    return <BlackKing/>
                }

            }
            if(piece.type === "Queen"){
                if(piece.color === "WHITE"){
                    return <WhiteQueen/>
                }
                else{
                    return <BlackQueen/>
                }

            }
            if(piece.type === "Rook"){
                if(piece.color === "WHITE"){
                    return <WhiteRook/>
                }
                else{
                    return <BlackRook/>
                }
            }
            if(piece.type === "Bishop"){
                if(piece.color === "WHITE"){
                    return <WhiteBishop/>
                }
                else{
                    return <BlackBishop/>
                }
            }
            if(piece.type === "Knight"){
                if(piece.color === "WHITE"){
                    return <WhiteKnight/>
                }
                else{
                    return <BlackKnight/>
                }
            }
            if(piece.type === "Pawn"){
                if(piece.color === "WHITE"){
                    return <WhitePawn/>
                }
                else{
                    return <BlackPawn/>
                }
            }
            if(piece.type === "Portal"){
                if(piece.color === "WHITE"){
                    return <WhitePortal/>
                }
                else{
                    return <BlackPortal/>
                }
            }
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