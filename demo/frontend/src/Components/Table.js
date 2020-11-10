import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper,
         Typography, Button, Grid, TextField, Checkbox, CircularProgress } from "@material-ui/core";
import {Link, Redirect} from "react-router-dom";
import axios from 'axios';
import Header from './Header';
import UserStats from "./UserStats";

const useStyles = makeStyles(theme => ({
  table: {
    minWidth: 600
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  paper: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    margin: `10px`,
    height: "100%",
    width: "99%",
    marginTop: theme.spacing(7)
  },
  link: {
    color: "rgba(0,0,0,0.65)",
    textDecoration: "none",
    marginLeft: "10%",
    alignSelf: "flex-start",
    "&:hover": {
      color: "rgba(0,0,0,1)"
    }
  }
}));

export default function SimpleTable(props) {

  const classes = useStyles();

  const [data, upDateData] = React.useState([]);
  const [firstLoad, setLoad] = React.useState(true);

  const [users, setUsers] = React.useState([]);
  const [selectedUsers, setSelectedUsers] = React.useState([]);

  const [searchString, setSearchString] = React.useState("");
  React.useEffect(() => {setUsers(getFoundUsers())}, [searchString]);
  const handleSearchStringChange = event => {setSearchString(event.target.value)};

  const [profileID, setProfileID] = React.useState({
      id: -1,
      name: "",
      shown: false
  });

  const [statusMessage, setStatusMessage] = React.useState("");

  const openStatsWindow = (uid, uname) => {
      setProfileID({
          id: uid,
          name: uname,
          shown: true
      });
  };
  const closeStatsWindow = () => {
      setProfileID({
          id: -1,
          name: "",
          shown: false
      });
  };
  let isLoading = true;

  const retrieveAccounts = () => {
    axios.get("/api/account").then(response => {upDateData(response.data);})
  }

  function getFoundUsers() {
    let newUsers = [];
    let search = searchString.toLowerCase();
    data.map(row => {
      let uname = row.username.toLowerCase();
      if (uname.includes(search)) {
        newUsers.push(row);
      }
    });
    return newUsers;
  }

  async function handleInvites(){
      let userNames = [];
      for(let i = 0; i < selectedUsers.length; i++){
          let senderID = props.user.id;
          let receiverID = selectedUsers[i].id;
          userNames.push(selectedUsers[i].name);
          const userIDs = {senderID, receiverID};

          //Create the match before the invite is sent out
          const response = await fetch("/api/createMatch", {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json"},
                redirect: "follow", // manual, *follow, error
                referrerPolicy: "no-referrer", // no-referrer, *client
                body: JSON.stringify(userIDs) // body data type must match "Content-Type" header
          });
          const matchID = parseInt(await response.json());
          let message = "Invite";
          const matchIDs = {senderID, receiverID, message, matchID};
          const inviteResponse = await fetch("/api/createInvite", {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json"},
                redirect: "follow", // manual, *follow, error
                referrerPolicy: "no-referrer", // no-referrer, *client
                body: JSON.stringify(matchIDs) // body data type must match "Content-Type" header
          });
      }
      if(selectedUsers.length > 0) {
          setStatusMessage("Invite(s) sent to selected user(s)");
          setSelectedUsers([]);
          setTimeout(function () {
              setStatusMessage("")
          }.bind(this), 2000);
      }
  }

  // credit to: https://material-ui.com/components/tables/
  const clickCheckBox = (event, row) => {
    let index = selectedUsers.indexOf(row);
    let newUser = [];

    if (index === -1) {
      newUser = newUser.concat(selectedUsers, row);
    } else if (index === 0) {
      newUser = newUser.concat(selectedUsers.slice(1));
    } else if (index === selectedUsers.length - 1) {
      newUser = newUser.concat(selectedUsers.slice(0, -1));
    } else if (index > 0) {
      newUser = newUser.concat(
        selectedUsers.slice(0, index),
        selectedUsers.slice(index + 1),
      );
    }
    setSelectedUsers(newUser);
  };

  const selectAllClick = (event) => {
    if (event.target.checked) {
      let newSelecteds = [];
      for(let i = 0; i < users.length; i++){
        if(users[i].username != props.user.username) {
          newSelecteds.push(users[i]);
        }
      }
      setSelectedUsers(newSelecteds);
      return;
    }
    console.log("selected: ", selectedUsers);
    setSelectedUsers([]);
  };

  if (!props.loggedInStatus){
      return (<Redirect to = "/"/>);
  }

  if (firstLoad) {
    retrieveAccounts();
    setLoad(false);
  }

  if (data.length > 0) isLoading = false;

  return (
    <div className={classes.paper}>
      <Header {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut}/>
      {isLoading ? (
        <CircularProgress />
      ) : (
        <TableContainer
          style={{ width: "80%", margin: "0 10px" }}
          component={Paper}
        >
          <Grid container alignItems="center">
            <Typography component="h1" variant="h5">
              User Directory
            </Typography>
          </Grid>
          <div>{ '\xa0' /* For spacing */ }</div>
          <Grid container>
            <TextField variant="outlined" fullWidth id="user-search" value={searchString}
                       label="Search Usernames" name="user-search" onChange={handleSearchStringChange}/>
          </Grid>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell width={15}>
                  <Checkbox 
                      // Todo: add select all functionality
                      onClick={(event) => selectAllClick(event)}
                  />
                </TableCell>
                <TableCell align="center">Username</TableCell>
                <TableCell align="center">Profile</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users?.map(row => (interpretRow(row)) )}
            </TableBody>
          </Table>
        </TableContainer>
      )}
      <Link className={classes.link} to="/dashboard">
        {" "}
        <Typography align="left">
          &#x2190; Back to Dashboard
        </Typography>{" "}
      </Link>
      <Button variant="contained" onClick={handleInvites} >
        Send Invite(s)
      </Button>
        {" "}
        {statusMessage}
    </div>
  );

  function interpretRow(row) {
    return (
        <TableRow key={row.email}>
          <TableCell width={15}>{renderCheckBox(row)}</TableCell>
          <TableCell align="center">{row.username}</TableCell>
          <TableCell align="center">
              <Button onClick={() => openStatsWindow(row.id, row.username)}>View Profile</Button>
              {renderStats(row.id)}
          </TableCell>
        </TableRow>
    );
  }

  function renderCheckBox(row) {
      if(row.username != props.user.username) {
          return (
              <Checkbox color="primary" checked={selectedUsers.includes(row, selectedUsers.indexOf(row))}
                        onClick={(event) => clickCheckBox(event, row)}/>
          );
      }
  }

  function renderStats(uid) {
      if(profileID.id == uid) {
          return (
              <UserStats {...props} open={profileID.shown} closeWindow={() => closeStatsWindow()}
                         uID={profileID.id} uname={profileID.name}/>
          );
      }
  }
}