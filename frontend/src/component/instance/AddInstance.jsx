/*
* @file addTopic.jsx
* @description〈一句话功能简述〉
* @author jansora

*/
import React, {useState} from 'react';


import {Button, Form, Grid, GridColumn, Header, Modal, Message} from "semantic-ui-react";

import GetTheme from "../hooks/GetTheme";


import {AddInstanceRequest} from "../../request/instance";
import {Divider} from "antd";

const AddInstance = (props) => {

  const {topic, setTopic} = props;


  const {open, setOpen} = props;

  const [name, setName] = useState(null);

  const [uri, setUri] = useState(null);

  const [loading, setLoading] = useState(null);


  const args = {
    name, uri
  };

  const add = () => {
    AddInstanceRequest(args, setOpen)
  };



  return (

      <Modal
        open={open}
        onClose={() => setOpen(false)}
        style={{width: "350px"}}
        dimmer="inverted"
      >
        <Header as='h3' attached='top' textAlign="center">
新建实例
        </Header>

            <Form style={{padding: "1rem"}} loading={loading}>
              <Grid columns="equal">
                <GridColumn width={16}>
              <Form.Input
                  required
                  label='标题' placeholder='请输入专栏标题' type='text'
                  value={name} onChange={event => setName(event.target.value)}/>
              <Form.Input label="url" placeholder="请输入连接url"
                          value={uri} onChange={event => setUri(event.target.value)}/>
                </GridColumn>
              </Grid>
              <Divider style={{margin: '20px 0 12px 0'}}/>

              <Button
                    fluid
                    // style={{ height: 114, marginTop: 19, width: '100%'}}
                    color={GetTheme()} content='新建实例' onClick={() => add()}
                />

            </Form>

      </Modal>

  )
}
export default AddInstance;
