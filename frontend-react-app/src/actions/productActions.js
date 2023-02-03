import {
  listProductsRequest, 
  listProductsSuccess, 
  listProductsFail,
  productReviewsRequest,
  productReviewsSuccess,
  productReviewsFail,
  productCreateReviewRequest,
  productCreateReviewSuccess,
  productCreateReviewFail,
  productDetailsRequest,
  productDetailsSuccess,
  productDetailsFail,
  productCreateRequest,
  productCreateSuccess,
  productCreateFail,
  productUpdateRequest,
  productUpdateSuccess,
  productUpdateFail,
} from "../reducers/productSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  getAllProductsDetailApi,
  getProductReviewsApi,
  createProductReviewApi,
  getProductDetailApi,
  createProductApi,
  updateProductDetailApi,
} from '../service/RestApiCalls';


export const listProductsAction = (pageNumber) => async (dispatch) => {
  try {
    dispatch(listProductsRequest());
    //Get All Products Detail
    const responseData = await getAllProductsDetailApi(pageNumber || 0);
    dispatch(listProductsSuccess(responseData));
  } catch (error) {
    //dispatch(listProductsFail(getErrorMessage(error)));
  }  
};

export const listProductReviewsAction = (productId) => async (dispatch) => {
  try {
    dispatch(productReviewsRequest());
    //Get Product Reviews
    const productReviews = await getProductReviewsApi(productId);
    dispatch(productReviewsSuccess(productReviews));
  } catch (error) {
    dispatch(productReviewsFail(getErrorMessage(error)));
  }
};

export const createProductReviewAction = (createProductReviewRequestBody) => async (dispatch) => {
  try {
    dispatch(productCreateReviewRequest());
    //Create Product Review    
    await createProductReviewApi(createProductReviewRequestBody);
    dispatch(productCreateReviewSuccess());
    dispatch(listProductDetailsAction(createProductReviewRequestBody.productId));
    dispatch(listProductReviewsAction(createProductReviewRequestBody.productId));
  } catch (error) {
    dispatch(productCreateReviewFail(getErrorMessage(error)));
  }
};

export const listProductDetailsAction = (productId) => async (dispatch) => {
  try {
    dispatch(productDetailsRequest());
    //Get Product Detail
    const productDetail = await getProductDetailApi(productId);
    dispatch(productDetailsSuccess(productDetail));
  } catch (error) {
    dispatch(productDetailsFail(getErrorMessage(error)));
  }
};

export const createProductAction = (productReqBody) => async (dispatch) => {
  try {
    dispatch(productCreateRequest());
    await createProductApi(productReqBody);
    dispatch(productCreateSuccess());
  } catch (error) {
    const message = error.response && error.response.data.message ? error.response.data.message : error.message;
    if (message === 'Not authorized, token failed') {
      //dispatch(logout());
    }
    dispatch(productCreateFail(message));
  }
}

export const updateProductAction = (productReqBody) => async (dispatch) => {
  try {
    dispatch(productUpdateRequest());
    
    //Update Product
    await updateProductDetailApi(productReqBody);

    dispatch(productUpdateSuccess());
    
    dispatch(listProductDetailsAction(productReqBody.productId));
  } catch (error) {
    const message = error.response && error.response.data.message ? error.response.data.message : error.message;
    if (message === 'Not authorized, token failed') {
      //dispatch(logout());
    }
    dispatch(productUpdateFail(message));    
  }
};



