package com.sametsafkan.mssssm.repository;

import com.sametsafkan.mssssm.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
