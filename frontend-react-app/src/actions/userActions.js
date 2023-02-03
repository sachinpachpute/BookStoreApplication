import {
  userLoginRequest,
  userLoginSuccess,
  userLoginFail,
  userLogout,
  userRegisterRequest,
  userRegisterSuccess,
  userRegisterFail,
  userDetailsRequest,
  userDetailsSuccess,
  userDetailsFail,
  userUpdateProfileRequest,
  userUpdateProfileSuccess,
  userUpdateProfileFail,
  userUpdateProfileReset
} from "../reducers/userSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  getUserInfoApi,
  getAccessToken,  
  postSignupApi,
  putUserInfoApi,
  getAllUsersApi,
  getUserApi,
} from '../service/RestApiCalls';

export const login = (code) => async (dispatch) => {
  try {
    dispatch(userLoginRequest());

    //Login
    const loginResponse = await getAccessToken(code);
    
    alert("now printing loginReponse");
    console.log(loginResponse);
    alert("now printing loginReponse.access_token");
    console.log(loginResponse.access_token);
    
    //alert(loginResponse.access_token);
    const userInfo = {
      token: loginResponse.access_token
    };

    localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //Get UserInfo
    const userInfoResponse = await getUserInfoApi();
    userInfoResponse.token = loginResponse.access_token;
    //userInfoResponse.refresh_token = loginResponse.refresh_token;
    
    dispatch(userLoginSuccess(userInfoResponse));
  
  } catch (error) {
    dispatch(userLoginFail(getErrorMessage(error)));
  }  
};

// export const login = (usernameOrEmail, password) => async (dispatch) => {
//   try {
//     dispatch(userLoginRequest());

//     const loginRequest = {
//       grant_type: 'password',
//       username:usernameOrEmail,
//       password: password
//     };

//     //Login
//     const loginResponse = await postLoginApi(loginRequest);

//     const userInfo = {
//       token: loginResponse.access_token
//     };

//     localStorage.setItem('userInfo', JSON.stringify(userInfo));
//     alert('inside login method in userActions 2')

//     //Get UserInfo
//     const userInfoResponse = await getUserInfoApi();
//     alert('inside login method in userActions 3')
//     userInfoResponse.token = loginResponse.access_token;
//     userInfoResponse.refresh_token = loginResponse.refresh_token;
    
//     dispatch(userLoginSuccess(userInfoResponse));
  
//   } catch (error) {
//     dispatch(userLoginFail(getErrorMessage(error)));
//   }  
// };

export const logout = () => (dispatch) => {
  localStorage.clear();
  console.log('LOGOUT ACTION!!!');
  dispatch(userLogout());
};

export const register = (userName, firstName, email, password) => async (dispatch) => {
  try {
    dispatch(userRegisterRequest());

    //SignUp
    const signUpRequest = {
      grant_type: 'password',
      userName,
      password,
      firstName,
      email
    };

    //SignUp
    await postSignupApi(signUpRequest);

    //Login
    const loginRequest = {
      grant_type: 'password',
      username: userName,
      password: password
    };
    const loginResponse = await getAccessToken(loginRequest);

    const userInfo = {
      token: loginResponse.access_token
    };
    localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //Get UserInfo
    const userInfoResponse = await getUserInfoApi();
    userInfoResponse.token = loginResponse.access_token;

    dispatch(userRegisterSuccess(userInfoResponse));    
    dispatch(userLoginSuccess(userInfoResponse));
    
    localStorage.setItem('userInfo', JSON.stringify(userInfoResponse));
  } catch (error) {
    dispatch(userRegisterFail(getErrorMessage(error)));    
  }
};

export const getUserDetailsAction = (userId) => async (dispatch) => {
  try {
    dispatch(userDetailsRequest());
    //Get User Detail
    let userInfoResponse;
    if (userId) {
      userInfoResponse = await getUserApi(userId);
    } else {
      //Get UserInfo
      userInfoResponse = await getUserInfoApi();
    }
    dispatch(userDetailsSuccess(userInfoResponse));
  } catch (error) {
    dispatch(userDetailsFail(getErrorMessage(error)));
  }
};

export const updateUserProfileAction = (user) => async (dispatch) => {
  try {
    dispatch(userUpdateProfileRequest());

    //Update userInfo
    await putUserInfoApi(user);

    const userInfo = JSON.parse(localStorage.getItem('userInfo'));

    const updatedUserInfo = {
      ...userInfo,
      ...user
    };

    dispatch(userUpdateProfileSuccess(updatedUserInfo));

    localStorage.setItem('userInfo', JSON.stringify(updatedUserInfo));
  } catch (error) {
    dispatch(userUpdateProfileFail(getErrorMessage(error)));
  }
};