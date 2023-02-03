import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { LinkContainer } from 'react-router-bootstrap';
import { Table, Button, Row, Col } from 'react-bootstrap';

import Message from '../components/Message';
import Loader from '../components/Loader';
import { listProductsAction } from '../actions/productActions';
import ReactPaginate from 'react-paginate';
import { listProducts} from "../reducers/productSlice";


const ProductListScreenTemp = () => {
const productList = useSelector((state) => state.productList);
const { loading, error, products, pageResponse } = productList;

const dispatch = useDispatch();

  useEffect(() => {
    dispatch(listProductsAction(0));
  }, []);

  return (
    <div>
      {products &&
        products.map((item, i) => (
          <div key={i}>
            {/* <p>{item.title}</p> */}
            <p>{item.productId} : {item.productName}</p>
          </div>
        ))}
    </div>
  );
};

export default ProductListScreenTemp;