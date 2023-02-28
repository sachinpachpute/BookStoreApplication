package com.sp.spring.feignclient.config;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Configuration
public class FeignConfig {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public static Request.Options requestOptions() {
    int ribbonReadTimeout = 70000;
    int ribbonConnectionTimeout = 60000;
    return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
  }

/*  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      HttpServletRequest request = ((ServletRequestAttributes) Objects
              .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
      requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
      *//*requestTemplate.header("Accept", "application/json");
      requestTemplate.header("header_1", "value_1");
      requestTemplate.header("header_2", "value_2");
      requestTemplate.header("header_3", "value_3");*//*
    };
  }*/

}
