package com.sp.spring.accountservice.controller;

import com.sp.spring.accountservice.service.UserService;
import com.sp.spring.accountservice.web.CreateUserRequest;
import com.sp.spring.accountservice.web.GetUserInfoResponse;
import com.sp.spring.accountservice.web.GetUserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class UserController {

  @GetMapping("/user")
  //@PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<GetUserResponse> getUser(
          @RequestParam("userName") Optional<String> userName
          , @RequestParam("userId") Optional<String> userId) {

    GetUserResponse user = null;
    if (userName.isPresent()) {
      user = userService.getUserByUserName(userName.get());
    } else if (userId.isPresent()) {
      user = userService.getUserByUserId(userId.get());
    }
    return ResponseEntity.ok(user);
  }
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
