package com.example.recharge_service.service;

import com.example.recharge_service.client.OperatorClient;
import com.example.recharge_service.client.PaymentClient;
import com.example.recharge_service.client.UserClient;
import com.example.recharge_service.dto.*;
import com.example.recharge_service.entity.Recharge;
import com.example.recharge_service.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OperatorClient operatorClient;

    @Autowired
    private PaymentClient paymentClient;

    public RechargeResponse initiateRecharge(Long userId, RechargeRequest request) {
        // 1. Validate User
        UserDto user = userClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Validate Operator and Plan
        OperatorDto operator = operatorClient.getOperatorById(request.getOperatorId());
        PlanDto plan = operatorClient.getPlanById(request.getPlanId());
        
        if (operator == null || plan == null || !plan.getOperatorId().equals(operator.getId())) {
            throw new RuntimeException("Invalid Operator or Plan");
        }

        // 3. Save Recharge as PENDING
        Recharge recharge = new Recharge();
        recharge.setUserId(userId);
        recharge.setOperatorId(operator.getId());
        recharge.setPlanId(plan.getId());
        recharge.setMobileNumber(request.getMobileNumber());
        recharge.setAmount(plan.getAmount());
        recharge.setStatus("PENDING");
        recharge = rechargeRepository.save(recharge);

        // 4. Call Payment Service
        PaymentRequest paymentRequest = new PaymentRequest(
                recharge.getId(),
                userId,
                plan.getAmount(),
                request.getPaymentMethod()
        );

        try {
            PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);
            
            // 5. Update Recharge Status based on Payment
            recharge.setStatus(paymentResponse.getStatus());
            recharge = rechargeRepository.save(recharge);

            return mapToResponse(recharge, "Recharge initiated successfully");
        } catch (Exception e) {
            recharge.setStatus("FAILED");
            rechargeRepository.save(recharge);
            return mapToResponse(recharge, "Recharge failed during payment processing");
        }
    }

    public RechargeResponse getRechargeById(Long id) {
        Recharge recharge = rechargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recharge not found"));
        return mapToResponse(recharge, "Success");
    }

    public List<RechargeResponse> getRechargesByUserId(Long userId) {
        return rechargeRepository.findByUserId(userId).stream()
                .map(r -> mapToResponse(r, "Success"))
                .collect(Collectors.toList());
    }

    private RechargeResponse mapToResponse(Recharge recharge, String message) {
    	return new RechargeResponse(
    	        recharge.getId(),
    	        recharge.getUserId(),
    	        recharge.getOperatorId(),
    	        recharge.getPlanId(),
    	        recharge.getMobileNumber(),
    	        recharge.getAmount(),
    	        recharge.getStatus(),
    	        recharge.getCreatedAt(),
    	        message
    	);
    }
}
