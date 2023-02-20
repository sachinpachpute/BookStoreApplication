package com.sp.spring.payment.controller;

import com.sp.spring.payment.service.PaymentMethodService;
import com.sp.spring.payment.web.CreatePaymentMethodRequest;
import com.sp.spring.payment.web.GetPaymentMethodResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/paymentMethod")
    public ResponseEntity<?> createPaymentMethod(@RequestBody @Valid CreatePaymentMethodRequest createPaymentMethodRequest){
        paymentMethodService.createPaymentMethod(createPaymentMethodRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/paymentMethod")
    public ResponseEntity<?> getAllMyPaymentMethods() {
        List<GetPaymentMethodResponse> allMyPaymentMethods = paymentMethodService.getAllMyPaymentMethods();
        return new ResponseEntity<>(allMyPaymentMethods, HttpStatus.OK);
    }

    @GetMapping("/paymentMethod/{paymentMethodId}")
    public ResponseEntity<GetPaymentMethodResponse> getMyPaymentMethodById(@PathVariable("paymentMethodId") String paymentMethodId) {
        GetPaymentMethodResponse paymentMethodDetail = paymentMethodService.getMyPaymentMethodById(paymentMethodId);
        return new ResponseEntity<>(paymentMethodDetail, HttpStatus.OK);
    }

}
