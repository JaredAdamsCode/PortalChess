
import React, {Component} from 'react';
import {IconButton, Menu, MenuItem} from '@material-ui/core';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import {Link} from 'react-router-dom';
import { useHistory } from "react-router-dom";

class AccountMenu extends Component {
  constructor(props){
    super(props);
    this.state = {
      anchorEl: null

    }
    this.handleClick = this.handleClick.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.logout = this.logout.bind(this);
  }

  handleClick = (event) => {
    this.setState({anchorEl: event.currentTarget});
  }

  handleClose = () => {
    this.setState({anchorEl: null});
  }

  logout = () =>{
    this.handleClose();
    console.log("props: ", this.props);
    this.props.handleLogOut();
  }

  render(){

    return(
      <div>
        <IconButton aria-label="Account Circle" aria-haspopup="true" onClick={this.handleClick}>
          <AccountCircleIcon />
        </IconButton>
        <Menu
        id="simple-menu"
        anchorEl={this.state.anchorEl}
        keepMounted
        open={Boolean(this.state.anchorEl)}
        onClose={this.handleClose}
        >
          <MenuItem component={ Link } to="/Dashboard">Dashboard</MenuItem>
          {/* <MenuItem onClick={this.handleClose}>My account</MenuItem> */}
          <MenuItem onClick={this.logout} component={ Link } to="/">Logout</MenuItem>
        </Menu>

      </div>

    );
  }

}
export default AccountMenu;