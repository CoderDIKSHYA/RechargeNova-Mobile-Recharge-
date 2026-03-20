package com.example.recharge_service.service;

import com.example.recharge_service.client.OperatorClient;
import com.example.recharge_service.client.PaymentClient;
import com.example.recharge_service.client.UserClient;
import com.example.recharge_service.dto.*;
import com.example.recharge_service.entity.Recharge;
import com.example.recharge_service.repository.RechargeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RechargeServiceTest {

    @InjectMocks
    private RechargeService rechargeService;

    @Mock
    private RechargeRepository rechargeRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private OperatorClient operatorClient;

    @Mock
    private PaymentClient paymentClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initiateRecharge_Success() {
        Long userId = 1L;
        RechargeRequest request = new RechargeRequest();
        request.setOperatorId(1L);
        request.setPlanId(10L);
        request.setMobileNumber("1234567890");
        request.setPaymentMethod("UPI");

        UserDto userDto = new UserDto();
        userDto.setId(userId);

        OperatorDto operatorDto = new OperatorDto();
        operatorDto.setId(1L);

        PlanDto planDto = new PlanDto();
        planDto.setId(10L);
        planDto.setOperatorId(1L);
        planDto.setAmount(new BigDecimal("199.00"));

        Recharge savedRecharge = new Recharge();
        savedRecharge.setId(100L);
        savedRecharge.setAmount(new BigDecimal("199.00"));

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("SUCCESS");

        when(userClient.getUserById(userId)).thenReturn(userDto);
        when(operatorClient.getOperatorById(1L)).thenReturn(operatorDto);
        when(operatorClient.getPlanById(10L)).thenReturn(planDto);
        when(rechargeRepository.save(any(Recharge.class))).thenReturn(savedRecharge);
        when(paymentClient.processPayment(any(PaymentRequest.class))).thenReturn(paymentResponse);

        RechargeResponse response = rechargeService.initiateRecharge(userId, request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        verify(rechargeRepository, times(2)).save(any(Recharge.class));
    }
}
