import { createSlice } from "@reduxjs/toolkit";

export const userLoginSlice = createSlice({
    name:'userLogin',
    initialState: {
      state: {},
    },
    reducers: {
      userLoginRequest: (state, action) => {
        state.loading = true;
      },
      userLoginSuccess: (state, action) => {
        state.loading = false;
        state.userInfo = action.payload;
      },
      userLoginFail: (state, action) => {
        state.loading = false;
        state.error = action.payload;
      },
      userLogout: (state, action) => {     
        localStorage.clear();
        sessionStorage.clear();
        window.location.href = '/';        
        state = {};   
      },
    }
});

export const userRegisterSlice = createSlice({
  name:'userRegister',
  initialState: {
    state: {},
  },
  reducers: {
    userRegisterRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    userRegisterSuccess: (state, action) => {
      state.loading = false;
      state.userInfo = action.payload;
    },
    userRegisterFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    userRegisterReset: (state, action) => {
      state.loading = false;
      state.error = null;
    },
    userRegisterLogout: (state, action) => {   
      window.location.href = '/login';    
      state = {};
    },
  }
});

// export const userUpdateProfileSlice = createSlice({
//   name:'userUpdateProfile',
//   initialState: {
//     state: {},
//   },
//   reducers: {
//     userUpdateProfileRequest: (state, action) => {
//       state.loading = true;
//     },
//     userUpdateProfileSuccess: (state, action) => {
//       state.loading = false;
//       state.success = true;
//       state.userInfo = action.payload;
//     },
//     userUpdateProfileFail: (state, action) => {
//       state.loading = false;
//       state.error = action.payload;
//     },
//     userUpdateProfileReset: (state, action) => {
//       state = {};
//     },
//   }
// });

export const userDetailsSlice = createSlice({
  name:'userDetails',
  initialState: {
    state: {},
  },
  reducers: {
    userDetailsRequest: (state, action) => {
      state.loading = true;
    },
    userDetailsSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload;
    },
    userDetailsFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    }
  }
});

export const userUpdateProfileSlice = createSlice({
  name:'updateUserProfile',
  initialState: {
    state: {},
  },
  reducers: {
    userUpdateProfileRequest: (state, action) => {
      state.loading = true;
    },
    userUpdateProfileSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      state.userInfo = action.payload;
    },
    userUpdateProfileFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    userUpdateProfileReset: (state, action) => {
      state = {};
    }
  }
});

export const { userLoginRequest, userLoginSuccess, userLoginFail, userLogout } = userLoginSlice.actions;
export const { userRegisterRequest, userRegisterSuccess, userRegisterFail, userRegisterLogout } = userRegisterSlice.actions;
export const { userDetailsRequest, userDetailsSuccess, userDetailsFail } = userDetailsSlice.actions;
export const { userUpdateProfileRequest, userUpdateProfileSuccess, userUpdateProfileFail, userUpdateProfileReset } = userUpdateProfileSlice.actions;