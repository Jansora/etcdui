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
import {message, notification} from "antd";
import {stringify} from "qs"





export const AddInstanceRequest = (data, setOpen) => {

  client.post('instance/insert', stringify(data))
    .then(response =>  {
      const { data } = response;
      if (data.status){
        notification.success({
          message: "Etcd实例-创建Etcd实例",
          description: `创建成功`,
          duration: 2,
        })
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
        notification.success({
          message: "Etcd实例-更新Etcd实例",
          description: `更新成功`,
          duration: 2,
        })
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


export const QueryInstanceRequest = (hash) => {


  const [instance, setInstance] = useState({
    title: '加载中',
  });
  const [loading, setLoading] = useState(true);
  useEffect(()=> {
    if(loading) {
      client.get(`instance/${hash}`)
        .then(response =>  {
          const { data } = response;
          if (data.status){
            setInstance(data.data)
          } else {
            message.error(data.message)
          }

        }).catch( e => {
      }).finally(()=> { setLoading(false)
      })
    }

  }, [hash, loading]);



  return [instance, setInstance, loading, setLoading];
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

          notification.success({
            message: "Etcd实例-获取Etcd实例列表",
            description: `共 ${data.data.length} 个Etcd实例`,
            duration: 2,
          })
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
        notification.success({
          message: "Etcd实例-删除Etcd实例",
          description: `删除成功`,
          duration: 2,
        })
        callback && callback()
      } else {
        message.error(data.message)
      }

    }).catch(e => {
  }).finally(() => {

  })
}
