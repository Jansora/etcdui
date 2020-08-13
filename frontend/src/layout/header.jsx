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

const Header = (props) => {

    return (
        <StyledHeader>
            <StyledHeaderLeft>
              <NavLink to="/instance">实例管理</NavLink>
            </StyledHeaderLeft>
          <div className="left">

                <Link to={'/'}><Icon name="home"/></Link>
                <Divider type="vertical" style={{margin: '0 10px'}}/>
                <NavLink to={"/post"} title={"博客"}>
                  <Icon name="blogger"/>
                </NavLink>
                <Divider type="vertical" style={{margin: '0 10px'}}/>
                <NavLink to={"/topic"} title={"专栏"}><Icon name="book"/>
                </NavLink>
                <Divider type="vertical" style={{margin: '0 10px'}}/>
                <NavLink to={"/project"} title={"项目"}>
                  <Icon name="paper plane"/>
                </NavLink>
                <Divider type="vertical" style={{margin: '0 10px'}}/>
                <NavLink to={"/playground"} title={"Playground"}>
                  <Icon name="code"/>
                </NavLink>


          </div>
          <div className="middle">

              <Head>{111}</Head>

          </div>
          <div className="right">


                <Divider type="vertical" style={{margin: '0 16px 0 50px'}}/>

                <Add/>
                <Divider type="vertical" style={{margin: '0 16px'}}/>

                <Theme/>

                <Divider type="vertical" style={{margin: '0 16px'}}/>

                <Divider type="vertical" style={{margin: '0 16px'}}/>
                <Help/>

                <Divider type="vertical" style={{margin: '0 16px'}}/>


          </div>
          <a target="_blank" rel='noopener noreferrer' href={"https://github.com/Jansora/OnlineCompiler"} style={{float: "right"}}>Github</a>
        </StyledHeader>
    )
}

export default Header;
