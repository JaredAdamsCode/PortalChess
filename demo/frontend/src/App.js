import React, { Component } from "react";
import {Route, Switch, BrowserRouter as Router, useHistory} from "react-router-dom";
import AddUser from "./Components/AddUser";
import Table from "./Components/Table";
import UserLogin from "./Components/UserLogin";
import Dashboard from "./Components/Dashboard";
import Inbox from "./Components/Inbox";
import Match from "./Components/Match";
import axios from 'axios';
import Game from "./Components/Game";



class App extends Component {
  constructor(props){
    super(props);
    this.state = {
      loggedInStatus: false,
      user: {},
      matchID: null
    }
    this.handleLogIn = this.handleLogIn.bind(this);
    this.handleLogOut = this.handleLogOut.bind(this);
    this.setMatchID = this.setMatchID.bind(this);
  }

  getLoggedInStatus(){
    axios.post('/api/login', this.state.user)
    // if successful, set user and logged in status
    .then(response => {
     this.handleLogIn(response);
    })
    // if fails, log the error and set loggedInStatus to false
    .catch(error => {
      console.log("error from get logged in status: ", error);
      this.handleLogOut();
    })
  }

  handleLogIn(data){
    this.setState({loggedInStatus: true});
    this.setState({user: data});
  }

  handleLogOut(){
    this.setState({
      loggedInStatus: false, 
      user: {},
      matchID: null
    });
  }

  setMatchID(currentMatchID){
    this.setState({matchID: currentMatchID});
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/test" render={
            props => (<UserLogin {...props} loggedInStatus={this.state.loggedInStatus}
              handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
              user={this.state.user} /> )}/>

          <Route exact path="/createAccount" render={
            props => (<AddUser {...props} loggedInStatus={this.state.loggedInStatus}
              handleLogIn={this.handleLogIn} handleLogOut={this.handleLogOut} 
              user={this.state.user}/>)}/>

          <Route exact path="/users" render={
            props => (<Table {...props} loggedInStatus={this.state.loggedInStatus}
              handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
              user={this.state.user}/>)}/>

          <Route exact path="/dashboard" render={
            props => (<Dashboard {...props} loggedInStatus={this.state.loggedInStatus}
              handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
              user={this.state.user} setMatchID={this.setMatchID} matchID={this.state.matchID}/>)}/>

          <Route exact path="/inbox" render={
            props => (<Inbox {...props} loggedInStatus={this.state.loggedInStatus}
                                 handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
                                 user={this.state.user} />)}/>
          <Route exact path="/" render={
            props => (<Match {...props} loggedInStatus={this.state.loggedInStatus}
                             handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
                             user={this.state.user} />)}/>

          <Route exact path="/game" render={
            props => (<Game {...props} loggedInStatus={this.state.loggedInStatus}
                             handleLogOut={this.handleLogOut} handleLogIn={this.handleLogIn}
                             user={this.state.user} setMatchID={this.setMatchID} matchID={this.state.matchID}/>)}/>

        </Switch>
      </Router>
    );
  }
}

export default App;