package com.example.recharge_service.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private Long rechargeId;
    private Long userId;
    private BigDecimal amount;
    private String paymentMethod;

    public PaymentRequest() {}

    public PaymentRequest(Long rechargeId, Long userId,
                          BigDecimal amount, String paymentMethod) {
        this.rechargeId = rechargeId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // getters setters
}