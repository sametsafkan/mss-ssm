package com.sametsafkan.mssssm.service;

import com.sametsafkan.mssssm.domain.Payment;
import com.sametsafkan.mssssm.domain.PaymentEvent;
import com.sametsafkan.mssssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> paymentPreAuthorization(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> paymentAuthorization(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> declinePaymentAuthorization(Long paymentId);
}
