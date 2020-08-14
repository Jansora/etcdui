/*
* @file data.jsx
* @author jansora
* @date 2020/2/16
*/


/*
* @file data.jsx
* @author jansora
* @date 2020/2/5
*/


import {useEffect, useState} from "react";
import {client} from "./index";
import {message, notification} from "antd";
import {stringify} from "qs"





export const AddDataRequest = (node, callback) => {

  client.post('data/insert', stringify(node))
    .then(response =>  {
      const { data } = response;
      if (data.status){
        message.success("保存成功")
        callback && callback()
      } else {
        message.error(data.message)
      }

    }).catch( e => {
  }).finally(()=> {
  })


  return null;
};



export const FindNodes = (uri, key) => {


  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(()=> {
    if(loading) {
      client.get(`data/find?${stringify({uri, key})}`)
        .then(response =>  {
          const { data } = response;
          if (data.status){
            setData(data.data)
          } else {
            message.error(data.message)
          }

        }).catch( e => {
      }).finally(()=> { setLoading(false)
      })
    }

  }, [uri, key, loading]);



  return [data, setData, loading, setLoading];
};

export const DeleteDataRequest = (data, callback) => {

  client.delete(`data/delete/${data}`)
    .then(response => {
      const {data} = response;
      if (data.status) {

        callback && callback()
      } else {
        message.error(data.message)
      }

    }).catch(e => {
  }).finally(() => {

  })
}
