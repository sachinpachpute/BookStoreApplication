import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Form, Button, Row, Col } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useLocation } from 'react-router-dom';
import Message from '../components/Message';
import { login } from '../actions/userActions';
import FormContainer from '../components/FormContainer';
import FullPageLoader from '../components/FullPageLoader';

window.Buffer = window.Buffer || require("buffer").Buffer;

const LoginScreen = (props) => {
  const [userNameOrEmail, setUserNameOrEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const userLogin = useSelector((state) => state.userLogin);
  const { loading, error, userInfo } = userLogin;

  let navigateLog = useNavigate()
  let location = useLocation()
  
//var desire = location.search.slice(1).split("&")[0].split("=")[1]
// Example: let's take url http://example.com?name=jon&country=us
// location.search will be equal to ?name=jon&country=us
// .slice(1) skips the ?, returning the rest of the string.
// .split("&")[0] splits it into two strings (name=jon and country=us) and takes first one
// .split("=")[1] splits name=jon into name and jon and takes the second one. Done!

  const redirect = location.search ? location.search.split('=')[1] : '/'

  useEffect(() => {
    if (userInfo) {
      navigateLog(`/${redirect}`);
    }
  }, [navigateLog, userInfo, redirect])

  const loginSubmitHandler = (e) => {
    e.preventDefault();
    dispatch(login(userNameOrEmail, password));
  };

  return (
    <div>
      <FormContainer>
        <h1>Sign In</h1>
        {error && <Message variant='danger'>{JSON.stringify(error)}</Message>}
        <Form onSubmit={loginSubmitHandler}>
          <Form.Group controlId='username'>
            <Form.Label>Email Address</Form.Label>
            <Form.Control
              placeholder='Username or Email'
              value={userNameOrEmail}
              onChange={(e) => setUserNameOrEmail(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Form.Group controlId='password'>
            <Form.Label>Password</Form.Label>
            <Form.Control
              placeholder='Password'
              type='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Button type='submit' variant='primary'>
            Sign In
          </Button>
        </Form>

        <Row className='py-3'>
          <Col>
            New Customer? <Link to={redirect ? `/register?redirect=${redirect}` : '/register'}>Register</Link>
          </Col>
        </Row>
      </FormContainer>
      {loading && <FullPageLoader></FullPageLoader>}
    </div>
  );
};

export default LoginScreen;
