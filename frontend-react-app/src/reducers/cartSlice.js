import { createSlice } from "@reduxjs/toolkit";
//import { ProductsFakeData } from "../FakeData";

export const cartAddSlice = createSlice({
    name:'cartAddItem',
    initialState: {   
      state: {},     
    },
    reducers: {
      cartAddItemRequest: (state, action) => {
        state.loading = true;
      },
      cartAddItemSuccess: (state, action) => {
        state.loading = false;
        state.success = true;
      },    
      cartAddItemFail: (state, action) => {
        state.loading = false;
        state.error = action.payload;
      },
      cartAddItemReset: (state, action) => {
        state = {};
      }  
    }
});

export const cartDetailSlice = createSlice({
  name:'cartDetails',
  initialState: {
    state: {},
  },
  reducers: {
    cartDetailsRequest: (state, action) => {
      state.loading = true;
    },
    cardDetailsSuccess: (state, action) => {
      state.loading = false;
      state.cart = action.payload;
    },
    cartDetailsFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    cartDetailsReset: (state, action) => {
      state = {};
    }
  }
});

export const cartRemoveSlice = createSlice({
  name:'cartRemoveItem',
  initialState: {        
  },
  reducers: {
    cartRemoveItemRequest: (state, action) => {
      state.loading = true;
    },
    cartRemoveItemSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
    },
    cartRemoveItemFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },  
    cartRemoveItemReset: (state, action) => {
      state = {};
    },   
  }
});

export const { cartAddItemRequest, cartAddItemSuccess, cartAddItemFail, cartAddItemReset } = cartAddSlice.actions;
export const { cartDetailsRequest, cardDetailsSuccess, cartDetailsFail, cartDetailsReset } = cartDetailSlice.actions;
export const { cartRemoveItemRequest, cartRemoveItemSuccess, cartRemoveItemFail, cartRemoveItemReset } = cartRemoveSlice.actions;
