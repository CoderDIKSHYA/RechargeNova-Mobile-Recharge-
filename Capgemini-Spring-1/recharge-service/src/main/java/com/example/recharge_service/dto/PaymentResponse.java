package com.example.recharge_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private Long id;
    private Long rechargeId;
    private Long userId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime transactionTime;

    public PaymentResponse() {}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}