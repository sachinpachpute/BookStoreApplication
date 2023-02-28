import {
  cartAddItemRequest, 
  cartAddItemSuccess,
  cartAddItemFail,
  cartRemoveItemRequest,
  cartRemoveItemSuccess,
  cartRemoveItemFail,
  cartSaveShippingAddress,
  cartSavePaymentMethod,
  cartDetailsRequest,
  cardDetailsSuccess,
  cartDetailsFail, 
} from "../reducers/cartSlice";

import {
listProductsRequest, 
  listProductsSuccess, 
  listProductsFail,
} from "../reducers/productSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  addToCartApi,
  getCartDetailsApi,
  removeCartItemApi,
  getAllProductsDetailApi,
} from '../service/RestApiCalls';


export const tempProductsAction = (pageNumber) => async (dispatch) => {
  try {
    dispatch(listProductsRequest());
    //Get All Products Detail
    const responseData = await getAllProductsDetailApi(pageNumber || 0);
    dispatch(listProductsSuccess(responseData));
  } catch (error) {
    dispatch(listProductsFail(getErrorMessage(error)));
  }  
};

export const addToCartAction = (addToCartRequestBody) => async (dispatch) => {
  try {
    //alert("inside addToCartAction");
    dispatch(cartAddItemRequest());
    //alert("inside addToCartAction:addToCartApi");
    await addToCartApi(addToCartRequestBody);
    dispatch(cartAddItemSuccess());
    dispatch(getCartDetailsAction());
  } catch (error) {
    dispatch(cartAddItemFail(getErrorMessage(error)));
  }  
};

export const removeFromCartAction = (cartItemId) => async (dispatch) => {
  try {
    dispatch(cartRemoveItemRequest());
    await removeCartItemApi(cartItemId);
    dispatch(cartRemoveItemSuccess());
    dispatch(getCartDetailsAction());
  } catch (error) {
    dispatch(cartRemoveItemFail(getErrorMessage(error)));
  }  
};

export const getCartDetailsAction = () => async (dispatch) => {
  try {
    dispatch(cartDetailsRequest());
    const cartResponse = await getCartDetailsApi();
    dispatch(cardDetailsSuccess(cartResponse));
  } catch (error) {
    dispatch(cartDetailsFail(getErrorMessage(error)));
  }  
};
/*
export const saveShippingAddress = (data) => async (dispatch) => {
  dispatch(cartSaveShippingAddress(data));
  localStorage.setItem('shippingAddress', JSON.stringify(data))  
};

export const savePaymentMethod = (data) => async (dispatch) => {
  dispatch(cartSavePaymentMethod(data));
  localStorage.setItem('paymentMethod', JSON.stringify(data))  
};*/




