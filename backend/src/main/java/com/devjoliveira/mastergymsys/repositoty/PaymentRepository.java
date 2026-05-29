package com.devjoliveira.mastergymsys.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
