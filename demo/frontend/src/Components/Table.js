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

export default function SimpleTable() {

  const classes = useStyles();

  const [data, upDateData] = React.useState([]);
  const [firstLoad, setLoad] = React.useState(true);
  let isLoading = true;

  const [searchString, setSearchString] = React.useState("");
  const handleSearchStringChange = event => {setSearchString(event.target.value)};

  async function sampleFunc() {
    let response = await fetch("/api/account");
    let body = await response.json();
    upDateData(body);
  }

  function interpretRow(row) {
    let search = searchString.toLowerCase();
    let uname = row.username.toLowerCase();
    if (searchString.length > 0 || uname.includes(search)) {
      return (
          <TableRow key={row.email}>
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
  }


  if (firstLoad) {
    sampleFunc();
    setLoad(false);
  }

  if (data.length > 0) isLoading = false;

  return (
    <div className={classes.paper}>
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
                <TableCell align="center">Username</TableCell>
                <TableCell align="center">Profile</TableCell>
                <TableCell align="center">Invite to Game</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data?.map(row => (interpretRow(row)) )}
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
    </div>
  );
}