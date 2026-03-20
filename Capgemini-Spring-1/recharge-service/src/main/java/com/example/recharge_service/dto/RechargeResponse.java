package com.example.recharge_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RechargeResponse {

    private Long id;
    private Long userId;
    private Long operatorId;
    private Long planId;
    private String mobileNumber;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
    private String message;

    public RechargeResponse() {}

    public RechargeResponse(Long id, Long userId, Long operatorId, Long planId,
                            String mobileNumber, BigDecimal amount,
                            String status, LocalDateTime createdAt, String message) {
        this.id = id;
        this.userId = userId;
        this.operatorId = operatorId;
        this.planId = planId;
        this.mobileNumber = mobileNumber;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.message = message;
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
}