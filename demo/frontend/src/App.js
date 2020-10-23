
import React, { Component } from "react";
import AddUser from "./Components/AddUser";
import Dashboard from "./Components/Dashboard";
import { Route, BrowserRouter as Router } from "react-router-dom";
import Table from "./Components/Table";

class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={AddUser} />
        <Route exact path="/view" component={Table} />
        <Route exact path="/dashboard" component={Dashboard} />
      </Router>
    );
  }
}

export default App;