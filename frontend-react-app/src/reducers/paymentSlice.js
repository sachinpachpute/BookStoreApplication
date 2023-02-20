import { createSlice } from "@reduxjs/toolkit";

export const paymentMethodSaveSlice = createSlice({
    name:'paymentMethodAdd',
    initialState: {   
      state: {},     
    },
    reducers: {
      paymentMethodAddRequest: (state, action) => {
        state.loading = true;
      },
      paymentMethodAddSuccess: (state, action) => {
        state.loading = false;
        state.success = true;
      },    
      paymentMethodAddFail: (state, action) => {
        state.loading = false;
        state.error = action.payload;
      },
      paymentMethodAddReset: (state, action) => {
        state = {};
      }  
    }
});

export const paymentMethodListSlice = createSlice({
  name:'paymentMethodList',
  initialState: {
        paymentMethods: [],
    },
  reducers: {
    paymentMethodListRequest: (state, action) => {
      state.loading = true;
    },
    paymentMethodListSuccess: (state, action) => {
      state.loading = false;
      state.paymentMethods = action.payload;
    },
    paymentMethodListFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    paymentMethodListReset: (state, action) => {
      state.paymentMethods = [];
    }
  }
});

export const { paymentMethodAddRequest, paymentMethodAddSuccess, paymentMethodAddFail, paymentMethodAddReset } = paymentMethodSaveSlice.actions;
export const { paymentMethodListRequest, paymentMethodListSuccess, paymentMethodListFail, paymentMethodListReset } = paymentMethodListSlice.actions;
