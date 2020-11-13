import React, {Component} from 'react';
import {Grid, IconButton, Menu, MenuItem, Typography, Popover} from '@material-ui/core';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import {Link} from 'react-router-dom';
import { useHistory } from "react-router-dom";
import Button from "@material-ui/core/Button";

class AccountMenu extends Component {
  constructor(props){
    super(props);
    this.state = {
      anchorEl: null,
      anchorPOP: null,
      name: props.user.username

    }
    this.handleClick = this.handleClick.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.handlePopup = this.handlePopup.bind(this);
    this.handleClosePOP = this.handleClosePOP.bind(this);
    this.unregisterAccount = this.unregisterAccount.bind(this);
    this.logout = this.logout.bind(this);
  }

  handleClick = (event) => {
    this.setState({anchorEl: event.currentTarget});
  }

  handleClose = () => {
    this.setState({anchorEl: null});
  }

  handlePopup = (event) => {
    this.setState({anchorPOP: event.currentTarget});
  }

  handleClosePOP = () => {
    this.setState({anchorPOP: null});
  }

  logout = () =>{
    this.handleClose();
    this.props.handleLogOut();
  }

  unregisterAccount = () => {
      console.log("Unregistering account");
      //this.handleClosePOP();
      //this.props.handleLogOut();
  }




  render(){
    return(
      <div>
        <Typography variant='h6'>
          {this.state.name}
        <IconButton aria-label="Account Circle" aria-haspopup="true" onClick={this.handleClick}>
          <AccountCircleIcon />
        </IconButton>
        </Typography>
        <Menu
        id="simple-menu"
        anchorEl={this.state.anchorEl}
        keepMounted
        open={Boolean(this.state.anchorEl)}
        onClose={this.handleClose}
        >
          <MenuItem component={ Link } to="/Dashboard">Dashboard</MenuItem>
          <MenuItem onClick={this.logout} component={ Link } to="/">Logout</MenuItem>
          <MenuItem onClick={this.handlePopup}> Unregister</MenuItem>
          <Popover
              id='simple-popover'
              anchorEl={this.state.anchorPOP}
              open={Boolean(this.state.anchorPOP)}
              onClose={this.handleClosePOP}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              transformOrigin={{
                vertical: 'top',
                horizontal: 'center',
               }}
          >
                  <Button fullWidth variant="contained" color="primary" preventDefault
                          onClick={this.unregisterAccount}>Confirm Unregister Account
                  </Button>
        </Popover>
        </Menu>

      </div>

    );
  }

}
export default AccountMenu;