import React, { useEffect } from 'react';
import { LinkContainer } from 'react-router-bootstrap';
import { Table, Button, Row, Col } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { isAdmin } from '../service/CommonUtils';
import Message from '../components/Message';
import Loader from '../components/Loader';
import { listProductsAction } from '../actions/productActions';
import ReactPaginate from 'react-paginate';

const ProductListScreen = ({ history, match }) => {
  useEffect(() => {


  }, []);


  return (
    <div>
        <button>Buy Cake</button>
    </div>
  );
}

export default ProductListScreen;
