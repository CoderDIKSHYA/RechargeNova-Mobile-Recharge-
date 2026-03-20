package com.example.recharge_service.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recharges")
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long operatorId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor (REQUIRED for JPA)
    public Recharge() {}

    // Parameterized constructor
    public Recharge(Long userId, Long operatorId, Long planId,
                    String mobileNumber, BigDecimal amount, String status) {
        this.userId = userId;
        this.operatorId = operatorId;
        this.planId = planId;
        this.mobileNumber = mobileNumber;
        this.amount = amount;
        this.status = status;
    }

    // Auto set timestamps
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // GETTERS

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getOperatorId() { return operatorId; }
    public Long getPlanId() { return planId; }
    public String getMobileNumber() { return mobileNumber; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // SETTERS

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setOperatorId(Long operatorId) { this.operatorId = operatorId; }
    public void setPlanId(Long planId) { this.planId = planId; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}