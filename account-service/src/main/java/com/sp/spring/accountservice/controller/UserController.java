package com.sp.spring.accountservice.controller;

import com.sp.spring.accountservice.service.UserService;
import com.sp.spring.accountservice.web.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/userInfo")
  public ResponseEntity<GetUserInfoResponse> getUserInfo() {
    GetUserInfoResponse userInfo = userService.getUserInfo();
    System.out.println("Hello 1");
    System.out.println("Hello 2");
    System.out.println("Hello 3");

    return new ResponseEntity<>(userInfo, HttpStatus.OK);
  }

  @GetMapping("/demoNew")
  public String demoNew() {
    System.out.println("Hello 1");
    System.out.println("Hello 2");
    System.out.println("Hello 3");
    return "Demo";
  }

}
