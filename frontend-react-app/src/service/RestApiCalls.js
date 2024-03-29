import { BACKEND_API_GATEWAY_URL, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, AUTHORIZATION_SERVER_BASE_URL } from '../constants/appConstants';
import axios from 'axios';
import qs from 'qs';
import store from '../store';
import { userLogout } from '../reducers/userSlice';

axios.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    // Prevent infinite loops
    if (error.response.status === 401 && originalRequest.url.includes('grant_type=refresh_token')) {
      localStorage.clear();
      store.dispatch(userLogout());
      return Promise.reject(error);
    }

    if (error.response.status === 401) {
      alert("refreshing token");
     
      const accTkn = JSON.parse(localStorage.getItem('userInfo'))?.access_token;      
      const refreshToken = JSON.parse(localStorage.getItem('userInfo'))?.refresh_token;
      
      if (refreshToken) {       
        const axiosConfig = {
          headers: {
            Authorization: 'Basic ' + btoa(CLIENT_ID + ':' + CLIENT_SECRET),
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        };

        const requestBody = {
          grant_type: 'refresh_token',
          refresh_token: refreshToken
        };
        const requestBodyEncoded = qs.stringify(requestBody);

        return axios
          .post(`${AUTHORIZATION_SERVER_BASE_URL}/oauth2/token`, requestBodyEncoded, axiosConfig)
          .then((response) => {
            const userInfo = JSON.parse(localStorage.getItem('userInfo'));
            const updatedUserInfo = {
              ...userInfo,
              token: response.data.access_token
            };
            
            localStorage.setItem('userInfo', JSON.stringify(updatedUserInfo));
            axios.defaults.headers['Authorization'] = 'Bearer ' + response.data.access_token;
            originalRequest.headers['Authorization'] = 'Bearer ' + response.data.access_token;
            return axios(originalRequest);
          })
          .catch((err) => {
            alert('Error while getting token using refresh token. Looks like even refresh token is expired.'+ err);
            console.error('Error while getting token using refresh token.', err);
            store.dispatch(userLogout());
          });       
      } else {
        console.log('Refresh token not available.');
        store.dispatch(userLogout());
      }
    }

    // specific error handling done elsewhere
    return Promise.reject(error);
  }
);

export const postSignupApi = (singupRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/auth/signUp`, singupRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const postLoginApi = async (loginRequestBody) => {
  const axiosConfig = {
    headers: {
      'Authorization': 'Basic ' + btoa(CLIENT_ID + ':' + CLIENT_SECRET),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  };
  const loginRequestBodyEncoded = qs.stringify(loginRequestBody);
  const responseData = await axios
    .post(`${AUTHORIZATION_SERVER_BASE_URL}/oauth2/token`, loginRequestBodyEncoded, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const getAccessToken = async (code) => {

  // const PKCEAuthCodeSecondStep = ( code ) => {
  //   let oidcURL = `${process.env.OIDC_IDP_URL}/token`;
  
  //   let params = qs.stringify( {
  //     grant_type: "authorization_code",
  //     redirect_uri: "http://localhost/login_oidc",
  //     client_id: process.env.OIDC_CLIENT_ID,
  //     code_verifier: localStorage.getItem( 'code_verifier' ),
  //     code
  //   } );
  
  //   localStorage.removeItem( 'code_verifier' );
  //   return axios.post( oidcURL, params,
  //     { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }
  //   ).then( res => {
  //     localStorage.setItem( 'access_token', res.data.access_token );
  //     return res.data
  //   } );
  // }

  //-----------------------------------------------------------
//alert('postLoginApi:code verifier before authorize call: ' + sessionStorage.getItem(('codeVerifier')));
//alert('postLoginApi: code challenge before authorize call: ' + sessionStorage.getItem(('codeChallenge')));
  const params = {
    grant_type: 'authorization_code',
    redirect_uri: REDIRECT_URI,
    client_id: CLIENT_ID,
    code_verifier: sessionStorage.getItem('codeVerifier'),
    code
  };
  const paramsEncoded = qs.stringify(params);  
  //sessionStorage.removeItem('codeVerifier');

  /*const axiosConfig = {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  };*/
  const responseData = await axios
    //.post(`${AUTHORIZATION_SERVER_BASE_URL}/oauth2/token`, paramsEncoded, axiosConfig)
    .post(`${AUTHORIZATION_SERVER_BASE_URL}/oauth2/token`, paramsEncoded)
    .then((response) => {
    //alert(response);
      return response.data;
    });
  return responseData;
};

export const getUserInfoApi = async () => {
  const axiosConfig = getAxiosConfig();
  //alert("Rest API calling user info api");
  //alert('access token : '+JSON.parse(localStorage.getItem('userInfo'))?.token)  ;
    const responseData = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/auth/userInfo`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getAllProductsDetailApi = async (pageNumber) => {    
    const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/products?page=${pageNumber}&size=8`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductDetailApi = async (productId) => {
  //alert("calling get product detail api");
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/product/${productId}`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const createProductApi = async (productReqBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios.post(`${BACKEND_API_GATEWAY_URL}/api/catalog/product`, productReqBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const updateProductDetailApi = async (productReqBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios.put(`${BACKEND_API_GATEWAY_URL}/api/catalog/product`, productReqBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getImageApi = async (imageId) => {
  //alert(imageId);
  //const axiosConfig = getAxiosConfig();
  //const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/image/${imageId}`, axiosConfig).then((response) => {
    const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/image/${imageId}`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductReviewsApi = async (productId) => {
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/review?productId=${productId}`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductCategories = async () => {
  const responseData = axios
    .get(`${BACKEND_API_GATEWAY_URL}/api/catalog/productCategories?direction=ASC&orderBy=PRODUCTCATEGORYNAME`)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const createProductReviewApi = async (createProductReviewRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/catalog/review`, createProductReviewRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const uploadImageApi = async (axiosConfig, formData) => {
  const accessToken = JSON.parse(localStorage.getItem('userInfo'))?.token;

  if (accessToken) {
    axiosConfig.headers.Authorization = `Bearer ${accessToken}`;
  }

  const responseData = await axios.post(`${BACKEND_API_GATEWAY_URL}/api/catalog/image/upload`, formData, axiosConfig).then((response) => {
    console.log('Resp ::', response.data);
    return response.data;
  });
  return responseData;
};

export const addToCartApi = async (addToCartRequestBody) => {
  //alert("inside addToCartApi");

  const axiosConfig = getAxiosConfig();
  const responseData = axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/order/cart/cartItem`, addToCartRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const removeCartItemApi = async (cartItemId) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.delete(`${BACKEND_API_GATEWAY_URL}/api/order/cart/cartItem/${cartItemId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getCartDetailsApi = async () => {
  const axiosConfig = getAxiosConfig();
  const cartDetails = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/order/cart`, axiosConfig).then((response) => {
    console.log(response.data);
    return response.data;
  });

  let sortedCart = {
    ...cartDetails,
    cartItems: cartDetails.cartItems.sort((a, b) => {
      return a.cartItemId.localeCompare(b.cartItemId);
    })
  };

  return sortedCart;
};

export const getAllMyOrdersApi = async () => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/order/order/myorders`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getUserApi = async (userId) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/account/user?userId=${userId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const putUserInfoApi = async (userInfoRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios
    .put(`${BACKEND_API_GATEWAY_URL}/api/auth/userInfo`, userInfoRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const getOrderApi = async (orderId) => {
  const axiosConfig = getAxiosConfig();  
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/order/order/${orderId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const saveAddressApi = async (addressRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/billing/address`, addressRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getAllAddressesApi = async () => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/billing/address`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const deleteAddressApi = async (addressId) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.delete(`${BACKEND_API_GATEWAY_URL}/api/billing/address/${addressId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const savePaymentMethodApi = async (cardRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/payment/paymentMethod`, cardRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getAllPaymentMethodsApi = async () => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/payment/paymentMethod`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const previewOrderApi = async (previewOrderRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/order/previewOrder`, previewOrderRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const placeOrderApi = async (placeOrderRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/order/order`, placeOrderRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

const getAxiosConfig = () => {
  const axiosConfig = {
    headers: {
      'Content-Type': 'application/json'    
    }
  };

  const accessToken = JSON.parse(localStorage.getItem('userInfo'))?.token;

  if (accessToken) {
    axiosConfig.headers.Authorization = `Bearer ${accessToken}`;
  }
  return axiosConfig;
};
