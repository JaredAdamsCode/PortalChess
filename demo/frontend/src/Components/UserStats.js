import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import {IconButton, AppBar, List, ListItem, ListItemText, Dialog, Divider, Toolbar} from "@material-ui/core";
import CloseIcon from '@material-ui/icons/Close';

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

    async function getGamesPlayed(userID) {
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
            return "0.00%";
        }
    }

    if (firstLoad) {
        getGamesPlayed(props.uID);
        getGamesWon(props.uID);
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
                        {"Stats for " + props.uname}
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
            </List>
        </Dialog>
    );
}