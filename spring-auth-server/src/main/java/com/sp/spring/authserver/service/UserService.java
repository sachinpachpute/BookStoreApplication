package com.sp.spring.authserver.service;


import com.sp.spring.authserver.web.GetUserInfoResponse;
import com.sp.spring.authserver.web.GetUserResponse;
import com.sp.spring.authserver.web.UpdateUserRequest;

import java.util.List;

public interface UserService {

  GetUserResponse getUserByUserName(String userName);

  GetUserResponse getUserByUserId(String userId);

  GetUserInfoResponse getUserInfo();

  void updateUserInfo(UpdateUserRequest updateUserRequest);

  void deleteUserById(String userId);

  List<GetUserResponse> getAllUsers();

  //void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
}
