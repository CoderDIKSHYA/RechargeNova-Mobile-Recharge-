package com.example.recharge_service.controller;

import com.example.recharge_service.dto.RechargeRequest;
import com.example.recharge_service.dto.RechargeResponse;
import com.example.recharge_service.service.RechargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    // Simulate extracting userId from JWT directly for now, usually done via SecurityContext
    // In a real scenario, Gateway validates JWT, and passes User ID in header
    @PostMapping
    public ResponseEntity<RechargeResponse> initiateRecharge(
            @RequestHeader(value = "X-User-Id", required = true) Long userId,
            @Valid @RequestBody RechargeRequest request) {
        return ResponseEntity.ok(rechargeService.initiateRecharge(userId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RechargeResponse> getRechargeById(@PathVariable Long id) {
        return ResponseEntity.ok(rechargeService.getRechargeById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RechargeResponse>> getRechargesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(rechargeService.getRechargesByUserId(userId));
    }
}
