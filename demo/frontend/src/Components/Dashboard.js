import React from "react";
import { Link } from "react-router-dom";

import {AccountCircle, SupervisorAccount} from "@material-ui/icons";

import {Box, Typography, IconButton, Divider, MenuItem, TextField, Grid, Menu,Button, Paper, Container} from '@material-ui/core';
import Header from './Header';

export default function Dashboard(props) {

    // console.log("dashboard props: ", props);
    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        document.location.href="/";
        handleClose();
    }

    let user = {
        name: 'testuser',
        matches: ['game1','game2', 'game3'],
        invitations: ['inv1','inv2']
    }

    return (
        
        <div >
            <Header {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut}/>
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
                            {user.invitations.map((invite) => (
                                <Typography variant='h5'>
                                    {invite}
                                </Typography>
                            ))}
                        </Paper>
                    </Grid>
                </Grid>



            </Box>
        </div>
    );
}