import React from "react";
import {Link, Redirect} from "react-router-dom";
import Header from "./Header";
import {Box, Divider, Grid, Paper, Typography, Button, IconButton, Popover} from "@material-ui/core";
import Chessboard from "./Chessboard";
import fillPieceArray from "./fillPieceArray";


export default function Game(props) {

    const [pendingList, upDatePending] = React.useState([]);
    const [firstLoad, setLoad] = React.useState(true);
    const [matchData, updateMatchData] = React.useState([]);
    const [chessboardData, setChessboardData] = React.useState([]);
    const [status, setStatus] = React.useState("\n");
    const [chessboard, setChessboard] = React.useState(<Chessboard  sendMove={sendMove}
                                                                    boardLayout={
                                                                        new Array(new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null),
                                                                        new Array(8).fill(null))}
                                                                    matchID={props.matchID}
                                                                    playerID={props.user.id}/>);
    const [anchorPOP, setAnchorPOP] = React.useState(null);

    if (!props.loggedInStatus){
        return (<Redirect to = "/"/>);
    }

    if (firstLoad) {
        getMatchData();
        setLoad(false);
    }

    const handlePop = () => {
        setAnchorPOP(!anchorPOP);
    };

    const handleClosePOP = () => {
        setAnchorPOP(null);
    };

    async function getMatchData(){
        let matchID = props.matchID;
        let response = await fetch('/api/getMatch/' + matchID);
        let body = await response.json();
        let chessData = JSON.parse(body.board);
        setChessboardData([...chessData]);
        setChessboard(null);
        setChessboard(<Chessboard sendMove={sendMove} boardLayout={chessData} matchID={props.matchID} playerID={props.user.id}/>);
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
        if(body.status !== "Illegal Move" && body.status !== "Board could not be instantiated"){
            setLoad(true);
            setStatus("\n");

        }
        else{
            setStatus(body.status);
        }

    }


    async function abandonMatch(userID) {
        let matchID = props.matchID;
        let response = await fetch('/api/getMatch/' + matchID);
        let body = await response.json();
        let opponentID = -1;
        if(body.senderID == userID) {
            opponentID = body.receiverID;
        }else {
            opponentID = body.senderID;
        }
        await fetch('api/abandonMatch/' + matchID +"/"+ opponentID + "/" +userID , {method: 'PATCH'});
        props.history.push('/dashboard');
    }

    return (

        <div >
            <Header {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut}/>
            <Box  style={{ height: '85vh' }} textAlign="center"  height="100%" p={15} pt={1} m={8} mb={2} mt={0}>
                <Box textAlign="center" p={2} pb={3} mb={3} m={2} mt={8}>
                    <Grid container >
                        <Grid container justify='center' >
                            <Grid item  >
                                <Typography variant='h3'>
                                    XGame
                                </Typography>
                            </Grid>
                        </Grid>
                    </Grid>

                </Box>
                <Grid  container spacing={40}>
                    <Grid item xs ={12} style={{paddingLeft: 0, paddingRight: 0}}>
                        <Paper>
                            <Typography variant='h4' align="center">
                                Chessboard
                            </Typography>
                            <Divider/>
                            <Typography variant='h6' align="center">
                                {status}
                            </Typography>
                            {chessboard}
                        </Paper>

                    </Grid>
                </Grid>
                <Button onClick={handlePop} align="right">
                    Abandon Game
                </Button>
                <Popover
                    id='simple-popover'
                    anchorEl={anchorPOP}
                    open={Boolean(anchorPOP)}
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'center',
                    }}
                    transformOrigin={{
                        vertical: 'bottom',
                        horizontal: 'center',
                    }}
                    onClose={handleClosePOP}
                >
                    <Button fullWidth variant="contained" color="primary" preventDefault
                            onClick={() => abandonMatch(props.user.id)}>Confirm Abandon Game
                    </Button>
                </Popover>
            </Box>
        </div>
    );

}