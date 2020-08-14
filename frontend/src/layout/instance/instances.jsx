/*
* @file instances.jsx
* @author index
* @date 2020/08/13
*/


import React from "react";
import {Grid, Table, Icon, Header, Button} from "semantic-ui-react";
import SetTitle from "../../component/hooks/SetTitle";
import {FindAllInstances, DeleteInstanceRequest} from "../../request/instance";
import {Link} from "react-router-dom";
import GetTheme from "../../component/hooks/GetTheme";

const Instances = (props) => {

    SetTitle("Etcd Node List")

    const [instances, total, loading, setLoading] = FindAllInstances();


    return (
        <Grid >
          <Table celled striped>
            <Table.Header>
              <Table.Row>
                <Table.HeaderCell width="4">Name</Table.HeaderCell>
                <Table.HeaderCell width="6">EndPoint</Table.HeaderCell>
                <Table.HeaderCell width='3'>Status</Table.HeaderCell>
                <Table.HeaderCell width='3'>Operation</Table.HeaderCell>
              </Table.Row>
            </Table.Header>

            <Table.Body>
              {
                instances.map(i =>        <Table.Row>
                  <Table.Cell collapsing>
                    <Link to={`/data/${i.value.uri.replace("://", "_")}`}>
                    <Icon name='folder' /> {i.value.name}
                    </Link>
                  </Table.Cell>
                  <Table.Cell>{i.value.uri}</Table.Cell>
                  <Table.Cell >
                    10 hours ago
                  </Table.Cell>
                  <Table.Cell textAlign='center'>
                    <Button size="tiny" basic
                            as={Link} to={`/data/${i.value.uri.replace("://", "_")}`}> Manage </Button>
                    <Button size="tiny" basic color={"red"} as="a" onClick={() =>
                      DeleteInstanceRequest(i.value.uri.replace("://", "_"),
                        () => setLoading(true))
                    }> Delete </Button>
                  </Table.Cell>
                </Table.Row>)
              }
            </Table.Body>
          </Table>
        </Grid>

    )
}

export default Instances;
