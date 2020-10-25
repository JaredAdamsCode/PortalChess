import React from "react";
import { Link } from "react-router-dom";

import {AccountCircle, SupervisorAccount} from "@material-ui/icons";

import {Box, Typography, IconButton, Divider, MenuItem, TextField, Grid, Menu,Button, Paper, Container} from '@material-ui/core';

export default function Dashboard() {

    const [anchorEl, setAnchorEl] = React.useState(null);
    const [inviteList, upDateData] = React.useState([]);
    const [firstLoad, setLoad] = React.useState(true);


    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const acceptInvite = (event) =>{

    }

    const rejectInvite = (event) =>{

    }

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        document.location.href="/";
        handleClose();
    }

    let user = {
        name: 'testuser',
        id: 5,
        matches: ['game1','game2', 'game3'],
        invitations: []
    }

    async function getInviteList(userID) {
        let response = await fetch('/api/getInviteList/' + userID);
        let body = await response.json();
        upDateData(body);
    }

     if (firstLoad) {
       getInviteList(5); //5 will eventually be this.account.id
       setLoad(false);
     }

    return (
        <div >
            <Box  style={{ height: '85vh' }}  color='white' bgcolor='black' textAlign="center"  height="100%" p={15} pt={1} m={8} mb={2} mt={0}>
                <Box color='black' bgcolor='white'  textAlign="center" p={2} pb={3} mb={3} m={2} mt={8}>
                    <Grid container >
                        <Grid container direction="row-reverse" justify='space-between' alignItems="baseline">
                            <Grid item>
                                <Typography variant='h7'>
                                    {user.name}
                                </Typography>
                                <IconButton aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                                    <AccountCircle/>
                                </IconButton>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={anchorEl}
                                    keepMounted
                                    open={Boolean(anchorEl)}
                                    onClose={handleClose}
                                >
                                    <MenuItem onClick={handleClose}>Profile</MenuItem>
                                    <MenuItem onClick={handleClose}>My account</MenuItem>
                                    <MenuItem onClick={handleLogout}>Logout</MenuItem>
                                </Menu>
                            </Grid>
                            <Grid item>{/*Placeholder*/}</Grid>
                            <Grid item>
                                <IconButton component={Link} to="/users">
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
                        <Button fullWidth ="true" variant="contained">Start New Match</Button>
                    </Grid>
                    <Grid item xs={6}>
                        <Paper>
                            <Typography variant='h4' align="center">
                                Current Matches
                            </Typography>
                            <Divider/>
                            {user.matches.map((match) => (
                                <Typography variant='h5'>
                                    {match}
                                </Typography>
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
                                {invite.message} from user id {invite.sender}
                                  <button className="extend-button"onClick={acceptInvite}>Accept</button>
                                  <button className="extend-button"onClick={rejectInvite}>Reject</button>
                                   </p>
                            ))}
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>
    );
}