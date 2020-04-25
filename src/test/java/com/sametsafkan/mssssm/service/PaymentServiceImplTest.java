package com.sametsafkan.mssssm.service;

import com.sametsafkan.mssssm.domain.Payment;
import com.sametsafkan.mssssm.domain.PaymentState;
import com.sametsafkan.mssssm.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
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
    @RepeatedTest(10)
    public void testPayment(){
        Payment savedPayment = paymentRepository.save(payment);
        paymentService.paymentPreAuthorization(savedPayment.getId());
        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());
        System.out.println(preAuthPayment);
        Assertions.assertTrue(PaymentState.PRE_AUTH.equals(preAuthPayment.getState())
                        || PaymentState.PRE_AUTH_ERROR.equals(preAuthPayment.getState()));
        paymentService.paymentAuthorization(preAuthPayment.getId());
        Payment authPayment = paymentRepository.getOne(preAuthPayment.getId());
        System.out.println(authPayment);
        Assertions.assertTrue(PaymentState.AUTH.equals(authPayment.getState())
                || PaymentState.AUTH_ERROR.equals(authPayment.getState()));
    }
}