package com.example.recharge_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.recharge_service.dto.OperatorDto;
import com.example.recharge_service.dto.PlanDto;

@FeignClient(name = "operator-service", url = "http://localhost:8082")
public interface OperatorClient {

    @GetMapping("/operators/{id}")
    OperatorDto getOperatorById(@PathVariable("id") Long id);

    @GetMapping("/plans/{id}")
    PlanDto getPlanById(@PathVariable("id") Long id);
}
