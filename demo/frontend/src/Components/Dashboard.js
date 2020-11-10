import React from "react";
import {Link, Redirect} from "react-router-dom";

import {Mail, SupervisorAccount} from "@material-ui/icons";

import {Box, Typography, IconButton, Divider, MenuItem, TextField, Grid, Menu,Button, Paper, Container} from '@material-ui/core';
import Header from './Header';

export default function Dashboard(props) {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const [inviteList, upDateData] = React.useState([]);
    const [matchesList, upDateMatches] = React.useState([]);

    const [firstLoad, setLoad] = React.useState(true);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    async function getMatchesList(userID) {
        //Gets list of matches with the userID
        let response = await fetch('/api/getMatchesList/' + userID);
        let body = await response.json();
        let filtered = [];

        //Filters this list to only show the In Progress matches
        for (let i = 0; i < body.length; i++) {
            if (body[i].status == "In Progress") {
                filtered.push(body[i]);
                }
        }

        //Adds the username into the matches list for each match to be shown in the list later
        for(const element of filtered){
                 let nameResponse = await fetch('/api/getUsername/' + element.senderID);
                 let nameBody = await nameResponse.json();
                 let username = nameBody.username;
                 element.username = username;
        }

        //Updates state of the matches list
        upDateMatches(filtered);
    }

    async function getInviteList(userID) {
        //Gets the list of notifications for the current user
        let response = await fetch('/api/getInviteList/' + userID);
        let body = await response.json();

        //Filters these notifications to only show the Invites
        let filtered = [];
        for (let i = 0; i < body.length; i++) {
            if (body[i].message == "Invite" || body[i].message == "invite") {
                filtered.push(body[i]);
            }
        }

        //Adds the username into the minvites list for each notification to be shown in the list later
        for(const element of filtered){
            let nameResponse = await fetch('/api/getUsername/' + element.senderID);
            let nameBody = await nameResponse.json();
            let username = nameBody.username;
            element.username = username;
        }

        upDateData(filtered);
    };

    if (!props.loggedInStatus){
        return (<Redirect to = "/"/>);
    }

     if (firstLoad) {
       getInviteList(props.user.id);
       getMatchesList(props.user.id);

       setLoad(false);
     };

    return (

        <div >
            <Header {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut}/>
            <Box  style={{ height: '85vh' }} textAlign="center"  height="100%" p={15} pt={1} m={8} mb={2} mt={0}>
                <Box textAlign="center" p={2} pb={3} mb={3} m={2} mt={8}>
                    <Grid container >
                        <Grid container direction="row-reverse" justify='space-between' alignItems="baseline">
                            <Grid item>
                                <Typography variant='h7'>
                                    Inbox
                                </Typography>
                                <IconButton component={ Link } to="/inbox" onClick={handleClick}>
                                    <Mail/>
                                </IconButton>
                            </Grid>
                            <Grid item>{/*For Spacing*/}</Grid>
                            <Grid item>
                                <IconButton component={Link} to="/users" {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut}>
                                    <SupervisorAccount/>
                                </IconButton>
                                <Typography variant="h7">Search Users</Typography>
                            </Grid>
                        </Grid>
                        <Grid container justify='center' >
                            <Grid item  >
                                <Typography variant='h3'>
                                    XGame
                                </Typography>
                            </Grid>
                        </Grid>
                    </Grid>

                </Box>
                <Grid container spacing={3}>

                    <Grid container>
                        <Button fullWidth ="true" variant="contained"
                            component={Link} to="/users"
                        >Start New Match</Button>
                    </Grid>
                    <Grid item xs={6}>
                        <Paper>
                            <Typography variant='h4' align="center">
                                Current Matches
                            </Typography>
                            <Divider/>
                            {matchesList.map((match) => (
                                <p key={match.id}>
                                {match.status} game with {match.username}
                                <button className="extend-button">Play Game</button>
                                </p>
                                ))}
                        </Paper>
                    </Grid>
                    <Grid item xs={6}>
                        <Paper>
                            <Typography variant='h4' align="center">
                                Invitations
                            </Typography>
                            <Divider/>
                               {inviteList.map(invite => (
                                <p key={invite.id}>
                                {invite.message} from {invite.username}
                                  <button className="extend-button">Accept</button>
                                  <button className="extend-button">Reject</button>
                                   </p>
                            ))}
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>
    );
}