import React, { Component } from "react";
import { Route, BrowserRouter as Router } from "react-router-dom";
import AddUser from "./Components/AddUser";
import Table from "./Components/Table";
import UserLogin from "./Components/UserLogin";
import HomePage from "./Components/HomePage";
import Dashboard from "./Components/Dashboard";

class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={HomePage}/>
        <Route exact path="/login" component={UserLogin} />
        <Route exact path="/createAccount" component={AddUser} />
        <Route exact path="/view" component={Table} />
        <Route exact path="/dashboard" component={Dashboard}/>
      </Router>
    );
  }
}

export default App;