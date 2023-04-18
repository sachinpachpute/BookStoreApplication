package com.sp.spring.authserver.controller;

import com.sp.spring.authserver.service.UserService;
import com.sp.spring.authserver.web.GetUserInfoResponse;
import com.sp.spring.authserver.web.GetUserResponse;
import com.sp.spring.authserver.web.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

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

  @GetMapping("/userInfo")
  public ResponseEntity<GetUserInfoResponse> getUserInfo() {
    GetUserInfoResponse userInfo = userService.getUserInfo();
    return new ResponseEntity<>(userInfo, HttpStatus.OK);
  }

  @PutMapping("/userInfo")
  public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
    userService.updateUserInfo(updateUserRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/demoNew")
  public String demoNew() {
    System.out.println("Hello 1");
    System.out.println("Hello 2");
    System.out.println("Hello 3");
    return "Demo";
  }

}
