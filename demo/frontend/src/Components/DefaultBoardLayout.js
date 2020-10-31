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
import * as constants from '../Constants/BoardConstants'

export default function DefaultBoardLayout(){

    const arr = new Array(64).fill(null);

    arr[constants.A1.index] = <WhiteRook/>;
    arr[constants.B1.index] = <WhiteKnight/>;
    arr[constants.C1.index] = <WhiteBishop/>;
    arr[constants.D1.index] = <WhiteQueen/>;
    arr[constants.E1.index] = <WhiteKing/>;
    arr[constants.F1.index] = <WhiteBishop/>;
    arr[constants.G1.index] = <WhiteKnight/>;
    arr[constants.H1.index] = <WhiteRook/>;

    for(let i = constants.A2.index; i < constants.A3.index; i++){
        arr[i] = <WhitePawn/>;
    }

    for(let i = constants.A7.index; i < constants.A8.index; i++){
        arr[i] = <BlackPawn/>;
    }

    arr[constants.A8.index] = <BlackRook/>;
    arr[constants.B8.index] = <BlackKnight/>;
    arr[constants.C8.index] = <BlackBishop/>;
    arr[constants.D8.index] = <BlackQueen/>;
    arr[constants.E8.index] = <BlackKing/>;
    arr[constants.F8.index] = <BlackBishop/>;
    arr[constants.G8.index] = <BlackKnight/>;
    arr[constants.H8.index] = <BlackRook/>;

    return arr;

}