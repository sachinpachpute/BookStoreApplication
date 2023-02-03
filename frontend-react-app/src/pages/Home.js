import React, { useEffect, useState } from 'react';
import { BACKEND_API_GATEWAY_URL } from '../constants/appConstants';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Card, Col, Form, Image, ListGroup, ListGroupItem, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import Message from '../components/Message';
import Rating from '../components/Rating';

import axios from 'axios';
import { getProductDetailApi } from '../service/RestApiCalls';

/*const ProductScreen = (props) => {
  const [qty, setQty] = useState(1);
  const [rating, setRating] = useState(0);
  const [reviewMessage, setReviewMessage] = useState('');
  const [productimageBase64, setProductimageBase64] = useState(null);
  const [product, setProduct] = useState(null);

  const dispatch = useDispatch();
  const productDetails = useSelector((state) => state.productDetails);
  const { loading, error } = productDetails;

  const productReviews = useSelector((state) => state.productReviews);
  const { loading: loadingProductReviews, error: errorProductReviews, reviews } = productReviews;

  const userLogin = useSelector((state) => state.userLogin);
  const { userInfo } = userLogin;

  const productReviewCreate = useSelector((state) => state.productReviewCreate);
  const { success: successProductReview, loading: loadingProductReview, error: errorProductReview } = productReviewCreate;

    useEffect(async () => {
        // setProductimageBase64(null);
        // dispatch(listProductDetailsAction(props.match.params.id));
        fetch('http://localhost:6001/product/7')
          .then(response => response.json())
          .then(data => console.log(data))

        *//*await getProductDetailApi(props.match.params.id).then((r) => {
          setProduct(r);*//*


      }, [product]);

  return (
    <>
    <div>{product}</div>
    </>
  );
};*/


const apidata ="http://localhost:6001/product";
const Home = (props) => {
    const [id, setID]=useState([]);
    const [name, setName]=useState([]);
    useEffect(async ()=>{
        /*async function getProductData(){
            const response = await axios.get(`${apidata}/2`);
            console.log(response);
            setID(response.data.productId);
            setName(response.data.productName);
        }*/
        const response = await axios.get(`${apidata}/2`);
        fetch(`${apidata}/2`)
          .then(response => response.json())
          .then(data => console.log(data))

          setID(response.data.productId);
          setName(response.data.productName);
    },[]);
    return (
        <div>
            <h2>{id}</h2>
            <p>{name}</p>
        </div>
    )
}

export default Home;
