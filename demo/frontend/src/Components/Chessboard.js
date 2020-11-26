import React, { Component } from "react";
import { Grid, Box, Button } from "@material-ui/core";
import * as constants from '../Constants/BoardConstants';
import BoardSquare from "./BoardSquare";
import DefaultBoardLayout from "./DefaultBoardLayout";
import fillPieceArray from "./fillPieceArray";


export default class Chessboard extends Component{

    constructor(props){
        super(props);

        this.selectSquare = this.selectSquare.bind(this);
        this.clearSelections = this.clearSelections.bind(this);
        this.isSelected = this.isSelected.bind(this);
        this.attemptMove = this.attemptMove.bind(this);


        this.state ={
            squaresStateArr: new Array(64).fill(false),
            fromPosition: null,
            fromPositionIndex: null,
            toPosition: null,
            toPositionIndex: null,
            pieceArr: fillPieceArray(this.props.boardLayout)
        }

    }

    attemptMove(){
        let fromPosition = this.state.fromPosition;
        let toPosition = this.state.toPosition;
        let matchId = this.props.matchID;
        let playerId = this.props.playerID;
        this.clearSelections();
        const toInput = { fromPosition, toPosition, matchId, playerId };
        console.log("toInput: ", toInput);
        this.props.sendMove(toInput);



    }

    selectSquare(position){
        let arr = this.state.squaresStateArr;
        if(!this.state.fromPosition){
            this.setState({fromPosition: position.value});
            this.setState({fromPositionIndex: position.index});
            arr[position.index] = true;
        }
        else if(this.state.fromPosition === position.value){
            arr[position.index] = false;
            arr[this.state.toPositionIndex] = false;
            this.setState({fromPosition: null});
            this.setState({fromPositionIndex: null});
            this.setState({toPosition: null});
            this.setState({toPositionIndex: null });

        }
        else{
            arr[this.state.toPositionIndex] = false;
            this.setState({toPositionIndex: position.index });
            this.setState({toPosition: position.value});
            arr[position.index] = true;
        }

        this.setState({squaresStateArr: arr});
    }

    clearSelections(){
        let arr =  new Array(64).fill(false);
        this.setState({squaresStateArr: arr});
        this.setState({fromPosition: null});
        this.setState({fromPositionIndex: null});
        this.setState({toPosition: null});
        this.setState({toPositionIndex: null });
    }

    isSelected(position){
        return (this.state.squaresStateArr[position.index]);
    }


    render(){

        return (
            <Grid container alignItems="center"  justify="center">
                <Grid item>
                    <Box bgcolor="black" p={1}>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A8} piece={this.state.pieceArr[constants.A8.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B8} piece={this.state.pieceArr[constants.B8.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C8} piece={this.state.pieceArr[constants.C8.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D8} piece={this.state.pieceArr[constants.D8.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E8} piece={this.state.pieceArr[constants.E8.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F8} piece={this.state.pieceArr[constants.F8.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G8} piece={this.state.pieceArr[constants.G8.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H8} piece={this.state.pieceArr[constants.H8.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A7} piece={this.state.pieceArr[constants.A7.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B7} piece={this.state.pieceArr[constants.B7.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C7} piece={this.state.pieceArr[constants.C7.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D7} piece={this.state.pieceArr[constants.D7.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E7} piece={this.state.pieceArr[constants.E7.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F7} piece={this.state.pieceArr[constants.F7.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G7} piece={this.state.pieceArr[constants.G7.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H7} piece={this.state.pieceArr[constants.H7.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A6} piece={this.state.pieceArr[constants.A6.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B6} piece={this.state.pieceArr[constants.B6.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C6} piece={this.state.pieceArr[constants.C6.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D6} piece={this.state.pieceArr[constants.D6.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E6} piece={this.state.pieceArr[constants.E6.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F6} piece={this.state.pieceArr[constants.F6.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G6} piece={this.state.pieceArr[constants.G6.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H6} piece={this.state.pieceArr[constants.H6.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A5} piece={this.state.pieceArr[constants.A5.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B5} piece={this.state.pieceArr[constants.B5.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C5} piece={this.state.pieceArr[constants.C5.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D5} piece={this.state.pieceArr[constants.D5.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E5} piece={this.state.pieceArr[constants.E5.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F5} piece={this.state.pieceArr[constants.F5.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G5} piece={this.state.pieceArr[constants.G5.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H5} piece={this.state.pieceArr[constants.H5.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A4} piece={this.state.pieceArr[constants.A4.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B4} piece={this.state.pieceArr[constants.B4.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C4} piece={this.state.pieceArr[constants.C4.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D4} piece={this.state.pieceArr[constants.D4.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E4} piece={this.state.pieceArr[constants.E4.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F4} piece={this.state.pieceArr[constants.F4.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G4} piece={this.state.pieceArr[constants.G4.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H4} piece={this.state.pieceArr[constants.H4.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A3} piece={this.state.pieceArr[constants.A3.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B3} piece={this.state.pieceArr[constants.B3.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C3} piece={this.state.pieceArr[constants.C3.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D3} piece={this.state.pieceArr[constants.D3.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E3} piece={this.state.pieceArr[constants.E3.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F3} piece={this.state.pieceArr[constants.F3.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G3} piece={this.state.pieceArr[constants.G3.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H3} piece={this.state.pieceArr[constants.H3.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A2} piece={this.state.pieceArr[constants.A2.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B2} piece={this.state.pieceArr[constants.B2.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C2} piece={this.state.pieceArr[constants.C2.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D2} piece={this.state.pieceArr[constants.D2.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E2} piece={this.state.pieceArr[constants.E2.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F2} piece={this.state.pieceArr[constants.F2.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G2} piece={this.state.pieceArr[constants.G2.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H2} piece={this.state.pieceArr[constants.H2.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                        <Grid container spacing={0}>
                            <Grid item>
                                <BoardSquare position={constants.A1} piece={this.state.pieceArr[constants.A1.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.B1} piece={this.state.pieceArr[constants.B1.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.C1} piece={this.state.pieceArr[constants.C1.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.D1} piece={this.state.pieceArr[constants.D1.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.E1} piece={this.state.pieceArr[constants.E1.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.F1} piece={this.state.pieceArr[constants.F1.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.G1} piece={this.state.pieceArr[constants.G1.index]} isWhite={false} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                            <Grid item>
                                <BoardSquare position={constants.H1} piece={this.state.pieceArr[constants.H1.index]} isWhite={true} isSelected={this.isSelected} selectSquare={this.selectSquare}/>
                            </Grid>
                        </Grid>
                    </Box>
                </Grid>
                <Grid container alignItems="center"  justify="center">
                    <Grid item><Button variant="contained" color="primary" onClick={this.clearSelections}>Clear</Button></Grid>
                    <Grid item><Button variant="contained" color="primary" onClick={this.attemptMove}>Move</Button></Grid>
                </Grid>

            </Grid>

        );
    }
}