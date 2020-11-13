import React from "react";
import {Redirect} from "react-router-dom";
import Header from "./Header";
import {Box, Divider, Grid, Paper, Typography} from "@material-ui/core";


export default function Game(props) {

    const [pendingList, upDatePending] = React.useState([]);
    const [firstLoad, setLoad] = React.useState(true);

    if (!props.loggedInStatus){
        return (<Redirect to = "/"/>);
    }

    if (firstLoad) {
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
                                Chess Game
                            </Typography>
                            <Divider/>
                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        </div>
    );
}