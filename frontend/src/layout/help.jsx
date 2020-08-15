/*
* @file help.jsx
* @description〈一句话功能简述〉
* @author jansora
* @date 2019-04-08 11:36
*/
import React from 'react';
import {Dropdown, Icon} from "semantic-ui-react";
import {NavLink} from "react-router-dom";

const Help = () => {



  return (
    <Dropdown
      trigger={
        <Icon
          name='help circle'
          title='help'
          style={{margin: 0}}
        />
      }
      icon={null}
      pointing='top right'
    >
      <Dropdown.Menu>
        <Dropdown.Item as={"a"} target="_blank" href="https://doc.etcdui.jansora.com"  icon='help circle'  text='help document' />
        <Dropdown.Item as={"a"} target="_blank" href="https://doc.etcdui.jansora.com"   icon='gem'  text='version info' />
      </Dropdown.Menu>
    </Dropdown>
  )
}
export default Help;
