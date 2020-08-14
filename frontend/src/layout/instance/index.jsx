/*
* @file playground.jsx
* @author index
* @date 2020/08/13
*/


import React from "react";
import {Grid} from "semantic-ui-react";
import {Route, Redirect} from 'react-router-dom';
import Instances from "./instances";

const Instance = (props) => {


    return (
        <Grid columns="equal" style={{marginTop: 30, height: "100%"}}>
          <Grid.Column width={2}/>
          <Grid.Column width={12}>
            <Route path="/instance" component={Instances} exact={true}/>

          </Grid.Column>
          <Grid.Column width={2}/>


        </Grid>

    )
}

export default Instance;
