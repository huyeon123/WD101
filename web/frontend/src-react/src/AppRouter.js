import React from "react";
import "./index.css";
import {Box, Typography} from "@material-ui/core";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Login from "./Login";
import App from "./App";
import SignUp from "./SignUp";

function Copyright() {
    return (
        <Typography variant={"body2"} color={"textSecondary"} align={"center"}>
            {"Copyright ⓒ "}
            Y.Hun, {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <Switch>
                        <Route path={"/login"}>
                            <Login/>
                        </Route>
                        <Route path="/signup">
                            <SignUp/>
                        </Route>
                        <Route path="/">
                            <App/>
                        </Route>
                    </Switch>
                    <Box mt={5}>
                        <Copyright/>
                    </Box>
                </Router>
            </div>
        );
    }
}

export default AppRouter;