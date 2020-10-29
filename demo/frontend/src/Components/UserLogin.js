import React from "react";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import { Link } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import { useHistory } from "react-router-dom";
import Header from './Header';
import axios from 'axios';
import { PictureInPictureSharp } from "@material-ui/icons";

const useStyles = makeStyles(theme => ({
    paper: {
        marginTop: theme.spacing(7),
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main
    },
    form: {
        width: "100%", // Fix IE 11 issue.
        marginTop: theme.spacing(3)
    },
    submit: {
        margin: theme.spacing(3, 0, 2)
    },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: "100%"
    }
}));

export default function UserLogin(props) {
    const classes = useStyles();
    const history = useHistory();

    const [data, upDateData] = React.useState([]);
    const [firstLoad, setLoad] = React.useState(true);
    let isLoading = true;

    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");

    const handleEmailChange = event => setEmail(event.target.value);
    const handlePasswordChange = event => setPassword(event.target.value);

    const [message, setMessage] = React.useState("Nothing saved in the session");


    async function loginFunc(toInput) {
         const response = await fetch("/api/login", {
             method: "POST", // *GET, POST, PUT, DELETE, etc.
             mode: "cors", // no-cors, *cors, same-origin
             cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
             credentials: "same-origin", // include, *same-origin, omit
             headers: {
                 "Content-Type": "application/json"
                 // 'Content-Type': 'application/x-www-form-urlencoded',
             },
             redirect: "follow", // manual, *follow, error
             referrerPolicy: "no-referrer", // no-referrer, *client
             body: JSON.stringify(toInput) // body data type must match "Content-Type" header
         });
         let body = await response.json();
         if(!body.message && body.status != 400){
             props.handleLogIn(body);
             props.history.push("/dashboard");
         }
         else{
             setMessage(body.message);
             props.handleLogOut();
         }

    }

    const handleSubmit = variables => {
        let toInput = { email: email, password: password };
        loginFunc(toInput);

    /*
        axios.post("/api/login", toInput)
        // if successful, set user and logged in status
            .then(response => {
                props.handleLogIn(response);
                props.history.push("/dashboard");
            })
       // if fails, log the error and set loggedInStatus to false
       .catch(error => {
         console.log("error from get logged in status: ", error);
         props.handleLogOut();
       })
    */

        setEmail("");
        setPassword("");
    };

    if (firstLoad) {
        // sampleFunc();
        setLoad(false);
    }

    return (
        <Container component="main" maxWidth="xs">
            <Header {...props} loggedInStatus={props.loggedInStatus} handleLogOut={props.handleLogOut} />

            <CssBaseline />
            <div className={classes.paper}>
                <Typography component="h1" variant="h5">
                    Login to Account
                </Typography>
                <form className={classes.form} noValidate>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="email"
                                value={email}
                                label="email"
                                name="email"
                                autoComplete="email"
                                onChange={handleEmailChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="password"
                                value={password}
                                label="password"
                                name="password"
                                autoComplete="password"
                                onChange={handlePasswordChange}
                            />
                        </Grid>
                    </Grid>
                    <Button
                        // type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        preventDefault
                        className={classes.submit}
                        onClick={handleSubmit}
                    >
                        LOGIN
                    </Button>
                </form>
                <Typography style={{ margin: 7 }} variant="body1">
                    Status: {message}
                </Typography>
            </div>
        </Container>
    );
}