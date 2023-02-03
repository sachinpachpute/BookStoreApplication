package com.sp.spring.feignclient.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-service")
public interface OrderFeignClient {


}
