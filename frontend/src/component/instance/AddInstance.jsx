/*
* @file addTopic.jsx
* @description〈一句话功能简述〉
* @author jansora

*/
import React, {useState} from 'react';


import {Button, Form, Grid, GridColumn, Header, Modal} from "semantic-ui-react";

import GetTheme from "../hooks/GetTheme";


import {AddInstanceRequest} from "../../request/instance";
import {Divider} from "antd";

const AddInstance = (props) => {

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
Add Etcd Node
        </Header>

            <Form style={{padding: "1rem"}} loading={loading}>
              <Grid columns="equal">
                <GridColumn width={16}>
              <Form.Input
                  required
                  label='title' placeholder='please input etcd name' type='text'
                  value={name} onChange={event => setName(event.target.value)}/>
              <Form.Input label="connect url" placeholder="please input connect url, such as http://127.0.0.1:2379 ?"
                          value={uri} onChange={event => setUri(event.target.value)}/>
                </GridColumn>
              </Grid>
              <Divider style={{margin: '20px 0 12px 0'}}/>

              <Button
                    fluid
                    // style={{ height: 114, marginTop: 19, width: '100%'}}
                    color={GetTheme()} content='Add' onClick={() => add()}
                />

            </Form>

      </Modal>

  )
}
export default AddInstance;
