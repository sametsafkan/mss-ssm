package com.sametsafkan.mssssm.config;

import com.sametsafkan.mssssm.domain.PaymentEvent;
import com.sametsafkan.mssssm.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

import static com.sametsafkan.mssssm.domain.PaymentEvent.*;

@SpringBootTest
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testStateMachine() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());
        sm.start();
        System.out.println(sm.getState().toString());
        sm.sendEvent(PRE_AUTH);
        System.out.println(sm.getState().toString());
        sm.sendEvent(PRE_AUTH_APPROVED);
        System.out.println(sm.getState().toString());
    }
}