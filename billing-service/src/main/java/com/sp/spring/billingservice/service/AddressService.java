package com.sp.spring.billingservice.service;


import com.sp.spring.billingservice.web.CreateAddressRequest;
import com.sp.spring.billingservice.web.GetAddressResponse;
import com.sp.spring.billingservice.web.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

  void createAddress(CreateAddressRequest createAddressRequest);

  List<GetAddressResponse> getAddress();

  void updateAddress(UpdateAddressRequest updateAddressRequest);

  GetAddressResponse getAddressById(String addressId);

  void deleteAddressById(String addressId);
}
