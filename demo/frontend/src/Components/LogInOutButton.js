import React, {Component} from 'react';
import {Button} from '@material-ui/core';
import {Link} from 'react-router-dom';
import AccountMenu from './AccountMenu';

class LogInOutButton extends Component{
  constructor(props){
    super(props);
    this.state = {

    }
  }

  render(){
    return (
      <div>
        {!this.props.loggedInStatus ? <Button component={ Link } to="/" >
          Login
        </Button> : null}
        { !this.props.loggedInStatus ? <Button component={ Link } to="/createAccount" >
          Signup
        </Button> : null}
        { this.props.loggedInStatus ? <AccountMenu {...this.props} handleLogOut={this.props.handleLogOut} />
           : null }
      </div>
    );
  }



} 

export default LogInOutButton;