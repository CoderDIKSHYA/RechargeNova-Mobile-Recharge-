package com.example.recharge_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.recharge_service.dto.PaymentRequest;
import com.example.recharge_service.dto.PaymentResponse;

@FeignClient(name = "payment-service", url = "http://localhost:8083")
public interface PaymentClient {

    @PostMapping("/api/payments")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
