package com.sp.spring.payment.service;

import com.sp.spring.payment.web.CreatePaymentMethodRequest;
import com.sp.spring.payment.web.GetPaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    void createPaymentMethod(CreatePaymentMethodRequest createPaymentMethodRequest);

    List<GetPaymentMethodResponse> getAllMyPaymentMethods();

    GetPaymentMethodResponse getMyPaymentMethodById(String paymentMethodId);
}
