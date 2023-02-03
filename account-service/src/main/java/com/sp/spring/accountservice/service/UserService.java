package com.sp.spring.accountservice.service;


import com.sp.spring.accountservice.web.GetUserInfoResponse;
import com.sp.spring.accountservice.web.GetUserResponse;

public interface UserService {

  GetUserResponse getUserByUserName(String userName);

  GetUserInfoResponse getUserInfo();

}
