package com.sp.spring.authserver.service;

import com.sp.spring.authserver.web.CreateUserResponse;
import com.sp.spring.authserver.web.SignUpRequest;

public interface AuthService {

  //CreateOAuthClientResponse createOAuthClient(CreateOAuthClientRequest createOAuthClientRequest);

  CreateUserResponse registerUser(SignUpRequest signUpRequest);
}
