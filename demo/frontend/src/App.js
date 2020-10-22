import React, { Component } from "react";
import { Route, BrowserRouter as Router } from "react-router-dom";
import AddUser from "./Components/AddUser";
import Table from "./Components/Table";
import UserLogin from "./Components/UserLogin";
import HomePage from "./Components/HomePage";

class App extends Component {
  render() {
    return (
      <Router>
        <Route expact path="/" component={HomePage}/>
        <Route exact path="/login" component={UserLogin} />
        <Route exact path="/createAccount" component={AddUser} />
        <Route exact path="/view" component={Table} />
      </Router>
    );
  }
}

export default App;