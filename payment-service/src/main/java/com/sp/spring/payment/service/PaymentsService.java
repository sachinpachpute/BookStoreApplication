package com.sp.spring.payment.service;

import com.sp.spring.payment.web.CreatePaymentRequest;
import com.sp.spring.payment.web.CreatePaymentResponse;

public interface PaymentsService {
    CreatePaymentResponse createPaymentRequest(CreatePaymentRequest createPaymentRequest);
}
