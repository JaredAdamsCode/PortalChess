import React from "react";
import { Grid, Box, Button } from "@material-ui/core";
import DefaultBoardLayout from "./DefaultBoardLayout";
import fillPieceArray from "./fillPieceArray";
import GameInterface from "./GameInterface";

import Chessboard from "./Chessboard";

export default function Match(props){

    const [pieceArr, setPieceArr] = React.useState(DefaultBoardLayout());
    const [remountCount, setRemountCount] = React.useState(0);
    const refresh = () => setRemountCount(remountCount + 1);


    const attemptMove = (fromPosition, toPosition) => {
        const toInput = { fromPosition, toPosition };
        sendMove(toInput);
        refresh();
    }

    async function sendMove(toInput) {
        const response = await fetch("/api/attemptMove", {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json"
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *client
            body: JSON.stringify(toInput) // body data type must match "Content-Type" header
        });
        let body = await response.json();
        if(!body.message){
            console.log("No message");
            console.log(body[0]);
            setPieceArr(fillPieceArray(body));

        }
        else{
            console.log("message");
            console.log(body);
        }

    }

    return (
            <Grid container>
                <Grid item>
                    <Chessboard/>
                </Grid>
            </Grid>
        );

}