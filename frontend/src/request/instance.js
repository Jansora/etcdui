/*
* @file instance.jsx
* @author jansora
* @date 2020/2/16
*/


/*
* @file instance.jsx
* @author jansora
* @date 2020/2/5
*/


import {useEffect, useState} from "react";
import {client} from "./index";
import {message} from "antd";
import {stringify} from "qs"


export const AddInstanceRequest = (data, setOpen) => {

  client.post('instance/insert', stringify(data))
    .then(response =>  {
      const { data } = response;
      if (data.status){
        message.success("added successfully")
        // setInstance(data.data)
        setOpen(false)
      } else {
        message.error(data.message)
      }

    }).catch( e => {
  }).finally(()=> {
  })


  return null;
};

export const UpdateInstanceRequest = (data, setOpen, setInstance) => {

  client.post('instance/update', stringify(data))
    .then(response =>  {
      const { data } = response;
      if (data.status){
        message.success("updated successfully");
        setInstance && setInstance(data.data)
        setOpen && setOpen(false)
      } else {
        message.error(data.message)
      }

    }).catch( e => {
  }).finally(()=> {
  })


  return null;
};



export const FindAllInstances = () => {

  const [instances, setInstances] = useState([]);
  const [total, setTotal] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if(loading) {
      client.get(`instance/findAll`).then(response => {
        const {data} = response;
        if (data.status) {
          setTotal(data.total)
          setInstances(data.data);
          message.success(`total ${data.data.length} nodes.`)
        } else {
          message.error(data.message);
        }
      }).finally();
    }
  }, [loading])

  return [instances, total, loading, setLoading];
};
export const DeleteInstanceRequest = (instance, callback) => {

  client.delete(`instance/delete/${instance}`)
    .then(response => {
      const {data} = response;
      if (data.status) {
        message.success("deleted successfully")
        callback && callback()
      } else {
        message.error(data.message)
      }

    }).catch(e => {
  }).finally(() => {

  })
}
