import {
  orderSaveBillingAddress,
  orderSaveShippingAddress,
  orderSavePaymentMethod,
  listOrderRequest,
  listOrderSuccess,
  listOrderSuccessFail,
  listOrderReset,
  listMyOrderRequest,
  listMyOrderSuccess,
  listMyOrderFail,
  orderPreviewRequest,
  orderPreviewSuccess,
  orderPreviewFail,
  orderPreviewReset,
  orderDetailsRequest,
  orderDetailsSuccess,
  orderDetailsFail,
  orderCreateRequest,
  orderCreateSuccess,
  orderCreateFail,
  orderCreateReset,
} from "../reducers/orderSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  getAllProductsDetailApi,
  getProductReviewsApi,
  createProductReviewApi,
  getProductDetailApi,
  createProductApi,
  updateProductDetailApi,
  getAllMyOrdersApi,
  previewOrderApi,
  placeOrderApi,
  getOrderApi
} from '../service/RestApiCalls';


export const listMyOrdersAction = (pageNumber) => async (dispatch) => {
  try {
    dispatch(listMyOrderRequest());
    //Get All my Orders
    const myOrdersData = await getAllMyOrdersApi();
    dispatch(listMyOrderSuccess(myOrdersData));
  } catch (error) {
    dispatch(listMyOrderFail(getErrorMessage(error)));
  }  
};

export const previewOrderAction = (previewOrderRequestBody) => async (dispatch) => {
  try {
    dispatch(orderPreviewRequest());

    //Preview Order
    const previewOrderData = await previewOrderApi(previewOrderRequestBody);

    dispatch(orderPreviewSuccess(previewOrderData));
  } catch (error) {
      dispatch(orderPreviewFail(getErrorMessage(error)));
    }
  };

  export const placeOrderAction = (placeOrderRequestBody) => async (dispatch) => {
    try {
      dispatch(orderCreateRequest());

      //Place Order
      const placeOrderData = await placeOrderApi(placeOrderRequestBody);
  
      dispatch(orderCreateSuccess(placeOrderData));
    } catch (error) {
      dispatch(orderCreateFail(getErrorMessage(error)));
    }
  };

  export const getOrderDetailsAction = (orderId) => async (dispatch) => {
    try {
      dispatch(orderDetailsRequest());
  
      //Get Order by Id
      const getOrderData = await getOrderApi(orderId);
  
      dispatch(orderDetailsSuccess(getOrderData));
    } catch (error) {
      dispatch(orderDetailsFail(getErrorMessage(error)));
    }
  };

export const saveBillingAddressIdToLocalStorage = (billingAddressId) => (dispatch) => {
  dispatch(orderSaveShippingAddress(billingAddressId));
  localStorage.setItem('billingAddressId', billingAddressId);
};

export const saveShippingAddressIdToLocalStorage = (shippingAddressId) => (dispatch) => {
  dispatch(orderSaveBillingAddress(shippingAddressId));
  localStorage.setItem('shippingAddressId', shippingAddressId);
};

export const savePaymentMethodIdToLocalStorage = (paymentMethodId) => (dispatch) => {
  dispatch(orderSavePaymentMethod(paymentMethodId));
  localStorage.setItem('paymentMethodId', paymentMethodId);
};



