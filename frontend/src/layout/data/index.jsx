/*
* @file index.jsx
* @author index
* @date 2020/08/14
*/


import React, {useState} from "react";
import {Grid, Header, Icon, Input, List} from "semantic-ui-react";
import {useParams} from 'react-router-dom';
import {AddDataRequest, FindNodes} from "../../request/data";
import SetTitle from "../../component/hooks/SetTitle";
import CodeEditor from "../../component/code-editor/CodeEditor";
import AddData from "../../component/data/AddData";
import {useDebouncedCallback} from "use-debounce";


const Data = (props) => {
  SetTitle(`Data Manage`)



  const params = useParams()
  const uri = params.uri.replace("_", "://");

  const [path, setPath] = useState("/");
  const [data, setData, loading, setLoading] = FindNodes(uri, path);

  const [key, setKey] = useState("/");
  const [value, setValue] = useState("")

  const [openNewData, setOpenNewData] = useState(false)




  const autoSearch_ = (path) => {
    setLoading(true)
  }

  const [autoSearch] = useDebouncedCallback(autoSearch_, 1000);


  return (
        <Grid columns="equal" style={{marginTop: 30, height: "100%"}}>
          <Grid.Column width={2}/>
          <Grid.Column width={12}>
            <Input
              fluid
              loading={loading}
              value={path}
              onChange={event => setPath(event.target.value) || autoSearch()}
              icon={<Icon name='search' circular link onClick={() => setLoading(true)} />}
              placeholder='Search...'
            />

            <Grid columns="equal" style={{marginTop: 30, height: "100%"}} divided>
              <Grid.Column width={4} >
                <Header as="h4" >
                  Hit Keys
                  <div style={{float: "right", color: "rgba(0,0,0,.4)"}}>
                    Not Found? Add
                    <a onClick={() => setOpenNewData(true)} > here</a>
                  </div>
                </Header>
                <List selection verticalAlign='middle' size="mini">
                  {
                    data.map(d =>  <List.Item key={d.key} onClick={() => {
                      setKey(d.key)
                      setValue(null)
                      setTimeout(() => setValue(d.value), 100)
                    }}>
                      <List.Content>
                        <List.Header>{d.key}</List.Header>
                      </List.Content>
                    </List.Item>)
                  }
                </List>
              </Grid.Column>

              <Grid.Column width={12}>

                <Header as="h4" >Current Key: {key}


                  <div style={{float: "right", color: "rgba(0,0,0,.4)"}}>
                    <a
                            style={{marginRight: 10}}
                            onClick={() => AddDataRequest({key, value, uri})}
                            > Save </a>

                    Needn't this data?  delete id
                    <a onClick={() => setOpenNewData(true)} > here </a>

                  </div>

                </Header>

                {
                  value !== null &&
                  <CodeEditor
                    force={false}
                    id={"code-editor-template"}
                    language="text"
                    value={value}
                    onChange={setValue}
                    style={{height: 600}}
                  />
                }


              </Grid.Column>

            </Grid>
          </Grid.Column>
          <Grid.Column width={2}/>

          {openNewData && <AddData uri={uri} open={openNewData} setOpen={setOpenNewData} />}
        </Grid>

    )
}

export default Data;
