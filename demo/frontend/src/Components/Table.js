import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { Link } from "react-router-dom";
import Typography from "@material-ui/core/Typography";
import CircularProgress from "@material-ui/core/CircularProgress";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Checkbox from '@material-ui/core/Checkbox';
import axios from 'axios';
import Header from './Header';

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

  const [selectedUsers, setSelectedUsers] = React.useState([]);

  const sampleFunc = () => {

    axios.get("/api/account")

    .then(response => {
      // console.log(response);
      upDateData(response.data);
    })
  }

  const [data, upDateData] = React.useState([]);
  const [firstLoad, setLoad] = React.useState(true);
  let isLoading = true;

  const [users, setUsers] = React.useState([]);

  const [searchString, setSearchString] = React.useState("");
  React.useEffect(() => {setUsers(getFoundUsers())}, [searchString]);
  const handleSearchStringChange = event => {setSearchString(event.target.value)};

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

  function interpretRow(row) {
    return (
        <TableRow key={row.email}>
          <TableCell>
            <Checkbox
              onClick={(event) => handleClick(event, row.email)}
            />
          </TableCell>

          <TableCell align="center">{row.username}</TableCell>
          <TableCell align="center">
            <Button>View Profile</Button>
          </TableCell>
          <TableCell align="center">
            <Button>Send Game Invite</Button>
          </TableCell>
        </TableRow>
    );
  }

  // credit to: https://material-ui.com/components/tables/
  const handleClick = (event, email) => {
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
    // console.log("selected: ", selectedUsers);
  };

  if (firstLoad) {
    sampleFunc();
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
                <TableCell >
                  <Checkbox 
                      // Todo: add select all functionality
                  />
                  Select All
                </TableCell>
                <TableCell align="center">Username</TableCell>
                <TableCell align="center">Profile</TableCell>
                <TableCell align="center">Invite to Game</TableCell>
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
}