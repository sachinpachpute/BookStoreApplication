package com.sp.spring.accountservice.service.impl;

import com.sp.spring.accountservice.repository.dao.User;
import com.sp.spring.accountservice.web.GetUserInfoResponse;
import com.sp.spring.accountservice.repository.UserRepository;
import com.sp.spring.accountservice.service.UserService;
import com.sp.spring.accountservice.web.GetUserResponse;
import com.sp.spring.common.exception.RunTimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public GetUserInfoResponse getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getName();

    GetUserResponse userByUserName = getUserByUserName(userName);

    return GetUserInfoResponse.builder()
        .userId(userByUserName.getUserId())
        .userName(userByUserName.getUserName())
        .firstName(userByUserName.getFirstName())
        .lastName(userByUserName.getLastName())
        .email(userByUserName.getEmail())
        .build();

  }
  @Override
  public GetUserResponse getUserByUserName(String userName) {

    Optional<User> userNameOrEmailOptional = userRepository
            .findByUserNameOrEmail(userName, userName);
    User userByUserName = userNameOrEmailOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    return GetUserResponse.builder()
            .userId(userByUserName.getUserId())
            .userName(userByUserName.getUserName())
            .firstName(userByUserName.getFirstName())
            .lastName(userByUserName.getLastName())
            .email(userByUserName.getEmail())
            .roles(userByUserName.getRoles())
            .build();
  }

  @Override
  public GetUserResponse getUserByUserId(String userId) {
    Optional<User> userIdOptional = userRepository.findByUserId(userId);
    User userById = userIdOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    return GetUserResponse.builder()
            .userId(userById.getUserId())
            .userName(userById.getUserName())
            .firstName(userById.getFirstName())
            .lastName(userById.getLastName())
            .email(userById.getEmail())
            .roles(userById.getRoles())
            .build();
  }

}
