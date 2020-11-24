import React, { Component } from "react";
import { Box, Button } from "@material-ui/core";
;

export default class BoardSquare extends Component{

    constructor(props){
        super(props);

        this.handleClick = this.handleClick.bind(this);

        this.state = {
            selected: false,
            squareStyle: null,
            piece: this.props.piece
        }

        this.blackSquareStyle = {
            position: "relative",
            height: 90,
            width: 90,
            background: "gray",

        };
        this.whiteSquareStyle = {
            position: "relative",
            height: 90,
            width: 90,
            background: "white",

        };
        this.selectedSquareStyle = {
            position: "relative",
            height: 90,
            width: 90,
            background: "lawngreen",

        };


    }

    /*componentWillReceiveProps(nextProps, nextContext) {
        if(nextProps.piece != this.props.piece){
            this.setState({piece: nextProps.piece});
        }
    }*/

    getDefaultSquareStyle(){
        if(this.props.isWhite){
            return this.whiteSquareStyle;
        }
        else{
            return this.blackSquareStyle;
        }
    }



    getSquareStyle(){
        if(this.props.isSelected(this.props.position)){
            return this.selectedSquareStyle;
        }
        else{
            return this.getDefaultSquareStyle();
        }
    }

    renderSquare(piece){
        return (
            <Box style={this.getSquareStyle()}>
                <Box pt={3}>
                    {piece}
                </Box>
            </Box>
        );
    }

    handleClick(){
        this.props.selectSquare(this.props.position);
    }


    render(){
        return (
            <Button onClick={this.handleClick} style={{
                padding:0,
                minHeight: 0,
                minWidth: 0,
            }}>
                {this.renderSquare(this.state.piece)}
            </Button>
        );
    }

}