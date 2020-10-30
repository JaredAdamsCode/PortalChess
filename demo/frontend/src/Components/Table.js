import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper,
         Typography, Button, Grid, TextField, Checkbox, CircularProgress } from "@material-ui/core";
import { Link } from "react-router-dom";
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

  // credit to: https://material-ui.com/components/tables/
  const clickCheckBox = (event, email) => {
    let index = selectedUsers.indexOf(email);
    let newUser = [];

    if (index === -1) {
      newUser = newUser.concat(selectedUsers, email);
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
      <Button variant="contained" > 
        Send Invite(s)
      </Button>
    </div>
  );

  function interpretRow(row) {
    return (
        <TableRow key={row.email}>
          <TableCell width={15}>
            <Checkbox
                onClick={(event) => clickCheckBox(event, row.email)}
            />
          </TableCell>

          <TableCell align="center">{row.username}</TableCell>
          <TableCell align="center">
              <Button onClick={() => openStatsWindow(row.id, row.username)}>View Profile</Button>
              {renderStats(row.id)}
          </TableCell>
        </TableRow>
    );
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