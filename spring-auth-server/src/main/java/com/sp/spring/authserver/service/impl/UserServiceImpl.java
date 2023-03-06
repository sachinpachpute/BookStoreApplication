package com.sp.spring.authserver.service.impl;

import com.sp.spring.authserver.repository.RoleRepository;
import com.sp.spring.authserver.repository.UserRepository;
import com.sp.spring.authserver.repository.dao.User;
import com.sp.spring.authserver.service.UserService;
import com.sp.spring.authserver.web.GetUserInfoResponse;
import com.sp.spring.authserver.web.GetUserResponse;
import com.sp.spring.authserver.web.UpdateUserRequest;
import com.sp.spring.common.exception.RunTimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  /*@Autowired
  private UserRoleService userRoleService;*/


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

  @Override
  public void updateUserInfo(UpdateUserRequest updateUserRequest) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getName();

    Optional<User> userNameOrEmailOptional = userRepository.findByUserNameOrEmail(userName, userName);

    User userByUserName = userNameOrEmailOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    if(updateUserRequest.getFirstName()!=null){
      userByUserName.setFirstName(updateUserRequest.getFirstName());
    }
    if(updateUserRequest.getLastName()!=null){
      userByUserName.setLastName(updateUserRequest.getLastName());
    }
    if(updateUserRequest.getPassword()!=null){
      String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
      userByUserName.setPassword(encodedPassword);
    }
    if(updateUserRequest.getEmail()!=null){
      userByUserName.setEmail(updateUserRequest.getEmail());
    }

    userRepository.save(userByUserName);
  }

  @Override
  public void deleteUserById(String userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getName();
    GetUserResponse userByUserId = getUserByUserId(userId);

    if(userName.equals(userByUserId.getUserName())){
      throw new RunTimeExceptionPlaceHolder("You cannot delete your own account!");
    }

    userRepository.deleteByUserId(userId);
  }

  @Override
  public List<GetUserResponse> getAllUsers() {

    Iterable<User> all = userRepository.findAll();
    List<GetUserResponse> allUsers = new ArrayList<>();
    all.iterator().forEachRemaining(u->{
      GetUserResponse userResponse = GetUserResponse.builder()
              .userId(u.getUserId())
              .userName(u.getUserName())
              .firstName(u.getFirstName())
              .lastName(u.getLastName())
              .email(u.getEmail())
              .roles(u.getRoles())
              .build();
      allUsers.add(userResponse);
    });

    return allUsers;
  }

  /*@Override
  public void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

    Optional<User> existingUser = userRepository.findByUserId(userId);

    User user = existingUser.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserId doesn't exist!!")
    );

    if(updateUserRequestFromAdmin.getFirstName()!=null){
      user.setFirstName(updateUserRequestFromAdmin.getFirstName());
    }
    if(updateUserRequestFromAdmin.getLastName()!=null){
      user.setLastName(updateUserRequestFromAdmin.getLastName());
    }
    if(updateUserRequestFromAdmin.getEmail()!=null){
      user.setEmail(updateUserRequestFromAdmin.getEmail());
    }

    if(user.getRoles().size()>0){
      MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
      List<String> existingRoles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
      mapUserToRolesRequest.setRoleNames(existingRoles);
      userRoleService.removeRolesFromUser(user.getUserName(), mapUserToRolesRequest);
    }

    if (updateUserRequestFromAdmin.getRoles().size() > 0) {
      MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
      mapUserToRolesRequest.setRoleNames(updateUserRequestFromAdmin.getRoles());
      userRoleService.mapUserToRoles(user.getUserName(), mapUserToRolesRequest);
    }

    userRepository.save(user);
  }*/

}
