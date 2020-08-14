/*
* @file add.jsx
* @description〈一句话功能简述〉
* @author jansora
* @date 2020-08-13 17:07
*/
import React, {useState} from 'react';
import {Dropdown, Icon} from "semantic-ui-react";
import AddInstance from "../../component/instance/AddInstance";

const Add = () => {

  const [openNewInstance, setOpenNewInstance] = useState(false);

  return (
    <React.Fragment>
      <Dropdown

        trigger={
          <Icon
            title='Add'
            name='add circle'
            style={{margin: 0}}
          />
        }
        icon={null}
        pointing='top left'
      >
        <Dropdown.Menu >
          <Dropdown.Item onClick={() => setOpenNewInstance(true)}
                         icon='folder open' text='Add Nodes' />

        </Dropdown.Menu>
      </Dropdown>
      {openNewInstance && <AddInstance open={openNewInstance} setOpen={setOpenNewInstance} />}

    </React.Fragment>
  )
}
export default Add;
