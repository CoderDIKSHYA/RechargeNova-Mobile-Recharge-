package com.example.recharge_service.repository;

import com.example.recharge_service.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
    List<Recharge> findByUserId(Long userId);
}
