/*
* @file header.jsx
* @author jansora
* @date 2020/2/4
*/


import React from "react";
import {StyledHeader, StyledHeaderLeft} from "../styled/header";
import {Header as Head, Icon} from "semantic-ui-react";
import Add from "./add";
import Help from "./help";
import {Divider} from "antd";

import {NavLink, Link} from "react-router-dom";
import Theme from "./theme";
import GetTitle from "../component/hooks/GetTitle";

const Header = (props) => {

  const title = GetTitle();

    return (
        <StyledHeader>

          <div className="left">
            <NavLink to="/instance" exact={true}>Etcd Nodes</NavLink>
            <Divider type="vertical" style={{margin: '0 10px'}}/>
            <NavLink to="/data" exact={false} disabled>Data Manage</NavLink>
          </div>
          <div className="middle">

              <Head>{title}</Head>

          </div>
          <div className="right">


                <Divider type="vertical" style={{margin: '0 16px 0 50px'}}/>

                <Add/>
                <Divider type="vertical" style={{margin: '0 16px'}}/>

                <Theme/>

                <Divider type="vertical" style={{margin: '0 16px'}}/>

            <a target="_blank" rel='noopener noreferrer'
               href={"https://github.com/Jansora/EtcdUI"}
               style={{float: "right"}}>
              <Icon name="github" />
            </a>
          </div>

        </StyledHeader>
    )
}

export default Header;
