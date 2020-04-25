package com.sametsafkan.mssssm.service;

import com.sametsafkan.mssssm.domain.Payment;
import com.sametsafkan.mssssm.domain.PaymentState;
import com.sametsafkan.mssssm.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentService paymentService;
    Payment payment;

    @BeforeEach
    public void setup(){
        payment = Payment.builder()
                .amount(BigDecimal.TEN)
                .build();
    }

    @Test
    @Transactional
    public void testPayment(){
        Payment savedPayment = paymentRepository.save(payment);
        paymentService.paymentPreAuthorization(savedPayment.getId());
        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());
        Assertions.assertTrue(PaymentState.PRE_AUTH.equals(preAuthPayment.getState()));
    }
}