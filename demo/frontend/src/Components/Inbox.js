import React from "react";
import {Box, Typography, Divider, Grid, Paper} from '@material-ui/core';
import Header from './Header';

export default function Inbox(props) {

    const [pendingList, upDatePending] = React.useState([]);
    const [firstLoad, setLoad] = React.useState(true);

    async function getPendingList(userID) {
        let response = await fetch('/api/getPendingList/' + userID);
        let body = await response.json();
        let filtered = [];
        for (let i = 0; i < body.length; i++) {
            if (body[i].message == "rejected" || body[i].message == "Rejected" || body[i].message == "accepted" || body[i].message == "Accepted" ) {
                filtered.push(body[i]);
            }
        }
        upDatePending(filtered);
    };

     if (firstLoad) {
       getPendingList(props.user.id);
       setLoad(false);
     };

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
                                Inbox
                            </Typography>
                            <Divider/>
                            {pendingList.map(invite => (
                                <p key={invite.id}>
                                     Invite to user ID {invite.receiver} status: {invite.message}
                                </p>
                            ))}
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>
    );
}