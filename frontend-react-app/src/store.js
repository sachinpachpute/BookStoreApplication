import {configureStore, createSlice } from '@reduxjs/toolkit';
import logger from 'redux-logger'
import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import { combineReducers } from '@reduxjs/toolkit';
import { 
  productListSlice,
  productDetailsSlice,
  productReviewsSlice,
  productReviewCreateSlice,
  productCreateSlice,
  productUpdateSlice,
} from './reducers/productSlice';
import { 
  orderSlice,
  orderListAllSlice,
  orderListMySlice,
  orderPreviewSlice,
  orderDetailsSlice,
  orderCreateSlice,
} from './reducers/orderSlice';
import{
  userLoginSlice,
  userRegisterSlice,
  userUpdateProfileSlice,
  userDetailsSlice,
} from './reducers/userSlice';
import{
  cartAddSlice,
  cartDetailSlice,
  cartRemoveSlice,
} from './reducers/cartSlice';
import{
  addressSaveSlice,
  addressListSlice,
  addressDeleteSlice,
} from './reducers/addressSlice';
import{
  paymentMethodSaveSlice,
  paymentMethodListSlice,
} from './reducers/paymentSlice';

import ToastMiddleware from './middlewares/ToastMiddleware';

const persistConfig = {
  key:'persist-key',
  storage,
}

const reducer = combineReducers({
  productList: productListSlice.reducer,
  productDetails: productDetailsSlice.reducer,
  productReviews: productReviewsSlice.reducer,
  productReviewCreate: productReviewCreateSlice.reducer,
  productCreate: productCreateSlice.reducer,
  productUpdate: productUpdateSlice.reducer,

  order: orderSlice.reducer,
  orderListAll:orderListAllSlice.reducer,
  orderListMy: orderListMySlice.reducer,
  orderPreview: orderPreviewSlice.reducer,
  orderDetails: orderDetailsSlice.reducer,
  orderCreate: orderCreateSlice.reducer,

  cart: cartDetailSlice.reducer,
  cartAdd: cartAddSlice.reducer,
  cartRemove: cartRemoveSlice.reducer,

  userLogin: userLoginSlice.reducer,
  userRegister: userRegisterSlice.reducer,
  userDetails: userDetailsSlice.reducer,
  userUpdateProfile: userUpdateProfileSlice.reducer,

  addressSave: addressSaveSlice.reducer,
  addressList: addressListSlice.reducer,
  addressDelete: addressDeleteSlice.reducer,

  paymentMethodSave: paymentMethodSaveSlice.reducer,
  paymentMethodListMy: paymentMethodListSlice.reducer,

});

const persistedReducer = persistReducer(persistConfig, reducer);

const store = configureStore({
  reducer:persistedReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});


export default store;
