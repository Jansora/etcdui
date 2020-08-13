import React from 'react';

import {Route, Switch} from 'react-router-dom'
import Hooks from "./component/hooks";
import Header from "./layout/header";
import styled from "styled-components";
import Instance from "./layout/instance";

const Layout = styled.main`
  padding-top: var(--header-height);
  height: calc(100% - var(--header-height));
  //width: 100%;
  margin: 0 50px;;
`;

const App = () => {

  return (
    <React.Fragment>
      <Hooks/>
      <Header/>
      <Layout >
        <Switch>
          <Route path="/:language" component={Instance} exact={false}/>
        </Switch>
      </Layout>

    </React.Fragment>
  );
}

export default App;
