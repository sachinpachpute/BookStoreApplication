import { createSlice } from "@reduxjs/toolkit";
//import { ProductsFakeData } from "../FakeData";

export const productListSlice = createSlice({
    name:'productList',
    initialState: {
        products: [],
    },
    reducers: {
      listProductsRequest: (state, action) => {
        state.loading = true;
        state.products = [];
      },
      listProductsSuccess: (state, action) => {
        state.loading = false;
        state.products = action.payload.content;
        state.pageResponse = action.payload;
      },
      listProductsFail: (state, action) => {
        state.loading = false;
        state.error = action.payload;
      },
    }
});

export const productDetailsSlice = createSlice({
  name:'productDetails',
  initialState: {
    products: [],
  },
  reducers: {
    productDetailsRequest: (state, action) => {
      state.loading = true;
    },
    productDetailsSuccess: (state, action) => {
      state.loading = false;
      state.products = action.payload;
    },
    productDetailsFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    }
  }
});

export const productReviewsSlice = createSlice({
  name:'productReviews',
  initialState: {
    reviews: [],
  },
  reducers: {
    productReviewsRequest: (state, action) => {
      state.loading = true;
    },
    productReviewsSuccess: (state, action) => {
      state.loading = false;
      state.reviews = action.payload;
    },
    productReviewsFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    }
  }
});

export const productReviewCreateSlice = createSlice({
  name:'productReviewCreate',
  initialState: {
    state: {},
  },
  reducers: {
    productCreateReviewRequest: (state, action) => {
      state.loading = true;
    },
    productCreateReviewSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
    },
    productCreateReviewFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    productCreateReviewReset: (state, action) => {
      state = {};
    }
  }
});

export const productCreateSlice = createSlice({
  name:'productCreate',
  initialState: {
    state: {},
  },
  reducers: {
    productCreateRequest: (state, action) => {
      state.loading = true;
    },
    productCreateSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      state.product = action.payload;
    },
    productCreateFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    productCreateReset: (state, action) => {
      state = {};
    }
  }
});

export const productUpdateSlice = createSlice({
  name:'productUpdate',
  initialState: {
    state: {},
  },
  reducers: {
    productUpdateRequest: (state, action) => {
      state.loading = true;
    },
    productUpdateSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      state.product = action.payload;
    },
    productUpdateFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    productUpdateReset: (state, action) => {
      state = {};
    }
  }
});

//export default productListSlice.reducer;
export const { listProductsRequest, listProductsSuccess, listProductsFail } = productListSlice.actions;
export const { productDetailsRequest, productDetailsSuccess, productDetailsFail } = productDetailsSlice.actions;
export const { productReviewsRequest, productReviewsSuccess, productReviewsFail } = productReviewsSlice.actions;
export const { productCreateReviewRequest, productCreateReviewSuccess, productCreateReviewFail, productCreateReviewReset } = productReviewCreateSlice.actions;
export const { productCreateRequest, productCreateSuccess, productCreateFail, productCreateReset } = productCreateSlice.actions;
export const { productUpdateRequest, productUpdateSuccess, productUpdateFail, productUpdateReset } = productUpdateSlice.actions;

//export const { listProducts,listProductsSuccess, listProductsFail, addUser, deleteUser, updateUsername } = productSlice.actions