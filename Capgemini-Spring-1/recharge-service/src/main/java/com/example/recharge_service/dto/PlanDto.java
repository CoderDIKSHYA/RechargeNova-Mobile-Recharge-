package com.example.recharge_service.dto;

import java.math.BigDecimal;

public class PlanDto {

    private Long id;
    private Long operatorId;
    private BigDecimal amount;
    private Integer validity;
    private String description;

    // Default constructor
    public PlanDto() {}

    // Parameterized constructor
    public PlanDto(Long id, Long operatorId, BigDecimal amount,
                   Integer validity, String description) {
        this.id = id;
        this.operatorId = operatorId;
        this.amount = amount;
        this.validity = validity;
        this.description = description;
    }

    // GETTERS

    public Long getId() { return id; }
    public Long getOperatorId() { return operatorId; }
    public BigDecimal getAmount() { return amount; }
    public Integer getValidity() { return validity; }
    public String getDescription() { return description; }

    // SETTERS

    public void setId(Long id) { this.id = id; }
    public void setOperatorId(Long operatorId) { this.operatorId = operatorId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setValidity(Integer validity) { this.validity = validity; }
    public void setDescription(String description) { this.description = description; }
}