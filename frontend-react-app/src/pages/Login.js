import React, {useState,useEffect} from 'react'
import axios from 'axios';
const apidata ="https://fakestoreapi.com/products";

const Login = () => {
    const [title, setTitle]=useState([]);
    const [description, setDescription]=useState([]);
    useEffect(()=>{
        async function getStoreData(){
            const response = await axios.get(`${apidata}/2`);
            console.log(response);
            setTitle(response.data.title);
            setTitle(response.data.description);
        }
        getStoreData();
    },[]);
    return (
        <div>
            <h2>{title}</h2>
            <p>{description}</p>
        </div>
    )
}

export default Login;