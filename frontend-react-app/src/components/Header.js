import React from 'react';
import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { isAdmin } from '../service/CommonUtils';
import { logout } from '../actions/userActions';
import { useEffect } from 'react';

const Header = (props) => {
  //alert("inside header");
  const userLogin = useSelector((state) => state.userLogin);
  const { userInfo } = userLogin;

  // useEffect(() => {
  //   if (userInfo) {
  //     const decodedJwt = parseJwt(userInfo.token);
  //     console.log(userInfo.token);
  //     //alert("Token Epiry: "+decodedJwt.exp);
  //     //alert("Date.now(): "+Date.now());
  //     if (decodedJwt.exp * 1000 < Date.now()) {
  //       alert("Token expired clearing local storage");
  //       localStorage.clear();
  //     }
  //   }
  // }, [userInfo]);

  // const parseJwt = (token) => {
  //   try {
  //     return JSON.parse(atob(token.split(".")[1]));
  //   } catch (e) {
  //     return null;
  //   }
  // };
  
  // if(userInfo === null){
  //   alert('userInfo is null');
  // } else {
  //   alert('userInfo: '+userInfo.firstName);
  //   alert('userInfo: '+userInfo.lastName);
  //   alert('userInfo: '+userInfo.email);
  //   alert('userInfo: '+userInfo.access_token);
  //   alert('userInfo: '+userInfo.userId);
  //   alert('userInfo: '+userInfo.userName);
  //   alert('userInfo: '+userInfo.token);
  // }
  
  const dispatch = useDispatch();

  const logoutHandler = () => {
    dispatch(logout());   
  };

  return (
    <header>
      <Navbar
        style={{
          background: 'linear-gradient(142deg, rgba(131,58,180,1) 0%, rgba(253,29,29,1) 68%, rgba(252,176,69,1) 100%)',
          border: '0',
          color: '#00000'
        }}
        className='navbar navbar-expand-lg navbar-dark'
        collapseOnSelect
      >
        <Container>
          <LinkContainer to='/'>
            <Navbar.Brand className='bookstore-brand'>My App</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle aria-controls='basic-navbar-nav' />
          <Navbar.Collapse id='basic-navbar-nav'>
            <Nav className='navbar-nav ml-auto'>
              <LinkContainer to='/cart'>
                <Nav.Link>
                  <i className='p-1 fas fa-shopping-cart'></i>Cart
                </Nav.Link>
              </LinkContainer>
              {userInfo ? (
                <NavDropdown title={userInfo.userName} id='username'>
                  <LinkContainer to='/userProfile'>
                    <NavDropdown.Item>Profile</NavDropdown.Item>
                  </LinkContainer>
                  <NavDropdown.Item onClick={logoutHandler}>Logout</NavDropdown.Item>
                </NavDropdown>
              ) : (
                <LinkContainer to='/login'>
                  <Nav.Link href='/login'>
                    <i className='p-1 fas fa-user'></i>Sign In
                  </Nav.Link>
                </LinkContainer>
              )}
              {userInfo && isAdmin() && (
                <NavDropdown title='Admin' id='adminmenu'>
                  <LinkContainer to='/admin/userlist'>
                    <NavDropdown.Item>Users</NavDropdown.Item>
                  </LinkContainer>
                  <LinkContainer to='/admin/productlist'>
                    <NavDropdown.Item>Products</NavDropdown.Item>
                  </LinkContainer>
                  <LinkContainer to='/admin/orderlist'>
                    <NavDropdown.Item>Orders</NavDropdown.Item>
                  </LinkContainer>
                </NavDropdown>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
};
export default Header;



