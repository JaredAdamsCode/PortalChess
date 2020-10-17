
import React, { Component } from "react";
import AddUser from "./Components/AddUser";
import { Route, BrowserRouter as Router } from "react-router-dom";
import Table from "./Components/Table";

class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={AddUser} />
        <Route exact path="/view" component={Table} />
      </Router>
    );
  }
}

export default App;