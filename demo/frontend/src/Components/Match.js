import React from "react";
import { Grid, Box, Button } from "@material-ui/core";



import Chessboard from "./Chessboard";

export default function Match(props){

    return (
            <Grid container>
                <Grid item>
                    <Chessboard/>
                </Grid>
            </Grid>
        );

}