import React, {Component} from 'react';
import {AppBar, Toolbar, Typography, Grid, IconButton} from '@material-ui/core';
import LogInOutButton from './LogInOutButton';
import HomeIcon from '@material-ui/icons/Home';
import {Link} from 'react-router-dom';

class Header extends Component {
  constructor(props){
    super(props);
    this.state = {

    }
  }

  render(){
    return(
      <AppBar position="static">
        <Toolbar>  
        <Grid
          justify="space-between" 
          container 
          spacing={2}
        >

          <Grid item>    
            <Typography variant="h6">
            <IconButton component={ Link } to="/">
              <HomeIcon />
            </IconButton>
            Strike Team Java Portal Chess
            </Typography>
          </Grid>

          <Grid item>
            <LogInOutButton {...this.props} loggedInStatus={this.props.loggedInStatus}
              handleLogOut={this.props.handleLogOut}
              /> 
          </Grid>
        </Grid>
        </Toolbar>
      </AppBar>
    );
  }

}
export default Header;