import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import {IconButton, AppBar, List, ListItem, ListItemText, Dialog, Divider, Toolbar} from "@material-ui/core";
import CloseIcon from '@material-ui/icons/Close';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles(theme => ({
    table: {
        minWidth: 600
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main
    },
    paper: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        margin: `10px`,
        height: "100%",
        width: "99%",
        marginTop: theme.spacing(7)
    },
    link: {
        color: "rgba(0,0,0,0.65)",
        textDecoration: "none",
        marginLeft: "10%",
        alignSelf: "flex-start",
        "&:hover": {
            color: "rgba(0,0,0,1)"
        }
    }
}));

export default function UserStats(props) {
    const classes = useStyles();
    const [firstLoad, setLoad] = React.useState(true);
    const [gamesPlayed, setGamesPlayed] = React.useState(0);
    const [gamesWon, setGamesWon] = React.useState(0);
    const [matchesList, upDateMatches] = React.useState([]);

    async function getGamesPlayed(userID, run) {
        let response = await fetch('/api/getGamesPlayed/' + userID);
        let body = await response.json();
        setGamesPlayed(body);
    };

    async function getGamesWon(userID) {
        let response = await fetch('api/getGamesWon/' + userID);
        let body = await response.json();
        setGamesWon(body);
    }

    const calcWinPercentage = () => {
        if(gamesPlayed > 0) {
            return ((gamesWon/gamesPlayed) * 100).toFixed(2) + "%";
        }else{
            return "Not Applicable";
        }
    }

        async function getMatchesList(userID) {
            //Gets list of matches with the userID
            let response = await fetch('/api/getMatchesList/' + userID);
            let body = await response.json();

            let filtered = [];

            //Filters this list to only show the In Progress matches
            for (let i = 0; i < body.length; i++) {
                if ((body[i].status !== "In Progress") && ((body[i]).status !== "Denied")) {
                    filtered.push(body[i]);
                    }
            }

            //Adds the username into the matches list for each match to be shown in the list later
            for(const element of filtered){

                if(element.winner !== null){
                    let winResponse = await fetch('/api/getUsername/' + element.winner);
                    let winBody = await winResponse.json();
                    let winnerName = winBody.username;
                    element.winner = winnerName;
                }

                if(element.loser !== null){
                    let loseResponse = await fetch('/api/getUsername/' + element.loser);
                    let loserBody = await loseResponse.json();
                    let loserName = loserBody.username;
                    element.loser = loserName;
                }

                if(element.startTime !== null){
                    let startDay = element.startTime.substring(0,10);
                    let startTime = element.startTime.substring(11,16);
                    element.startTime = (startDay + " " + startTime);
                }

                if(element.endTime !== null){
                    let endDay = element.endTime.substring(0,10);
                    let endTime = element.endTime.substring(11,16);
                    element.endTime = (endDay + " " + endTime);
                }
            }

            //Updates state of the matches list
            upDateMatches(filtered);
        }

    if (firstLoad) {
        getGamesPlayed(props.uID);
        getGamesWon(props.uID);
        getMatchesList(props.uID);
        setLoad(false);
    };

    return (
        <Dialog open={props.open} aria-labelledby="user-stats" fullScreen={true}>
            <AppBar className={classes.appBar}>
                <Toolbar>
                    <IconButton edge="start" onClick={props.closeWindow} aria-label="close">
                        <CloseIcon/>
                    </IconButton>
                    <Typography variant="h6" className={classes.title}>
                        {props.uname}
                    </Typography>
                </Toolbar>
            </AppBar>
            <List>
                <ListItem/><ListItem/><ListItem/><ListItem/>
                <ListItem>
                    <ListItemText primary="Games Won/Played" secondary={gamesWon + "/" + gamesPlayed}/>
                </ListItem>
                <Divider/>
                <ListItem>
                    <ListItemText primary="Win Percentage" secondary={calcWinPercentage()}/>
                </ListItem>
                <Divider/>
            </List>
              <TableContainer component={Paper}>
                  <Table className={classes.table} size="small" aria-label="a dense table">
                    <TableHead>
                      <TableRow>
                        <TableCell align="left">Winner</TableCell>
                        <TableCell align="left">Loser</TableCell>
                        <TableCell align="left">Status</TableCell>
                        <TableCell align="left">Start Time</TableCell>
                        <TableCell align="left">End Time</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {matchesList.map((match) => (
                        <TableRow key={match.winner}>
                          <TableCell align="left">{match.winner}</TableCell>
                          <TableCell align="left">{match.loser}</TableCell>
                          <TableCell align="left">{match.status}</TableCell>
                          <TableCell align="left">{match.startTime.toString()}</TableCell>
                          <TableCell align="left">{match.endTime}</TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </TableContainer>
        </Dialog>
    );
}