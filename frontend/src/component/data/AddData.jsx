/*
* @file addTopic.jsx
* @description〈一句话功能简述〉
* @author jansora

*/
import React, {useState} from 'react';


import {Button, Form, Grid, GridColumn, Header, Modal, Message} from "semantic-ui-react";

import GetTheme from "../hooks/GetTheme";


import {AddDataRequest} from "../../request/data";

import {Divider} from "antd";
import CodeEditor from "../code-editor/CodeEditor";

const AddData = (props) => {

  const {uri} = props;


  const {open, setOpen} = props;

  const [key, setkey] = useState(null);

  const [value, setValue] = useState("Input your value here...");

  const [loading, setLoading] = useState(null);


  const args = {
    key, value, uri
  };

  const add = () => {
    AddDataRequest(args, () => setOpen(false))

  };



  return (

      <Modal
        open={open}
        onClose={() => setOpen(false)}
        style={{width: "1000px"}}
        dimmer="inverted"
      >
        <Header as='h3' attached='top' textAlign="center">
          Add Data
        </Header>



          <Form style={{padding: "1rem"}} loading={loading}>
            <Form.Input
              required
              label='key' placeholder='input your key Here' type='text'
              value={key} onChange={event => setkey(event.target.value)}
            />
          </Form>

          <div style={{margin: "0 14px 10px 14px"}}>
                    {
                      <CodeEditor
                        force={false}
                        id={"code-editor-template"}
                        language="text"
                        value={value}
                        onChange={setValue}
                        style={{height: 450}}
                      />
                    }
            <Divider style={{margin: '8px 0 8px 0'}}/>
            <Button
              fluid
              // style={{ height: 114, marginTop: 19, width: '100%'}}
              color={GetTheme()} content='add' onClick={() => add()}
            />
          </div>





      </Modal>

  )
}
export default AddData;
