package com.sametsafkan.mssssm.config.action;

import com.sametsafkan.mssssm.domain.PaymentEvent;
import com.sametsafkan.mssssm.domain.PaymentState;
import com.sametsafkan.mssssm.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        log.info("PreAuth was called");
        //Added randomness to preAuth for approve some of the request and decline rest of it.
        if(new Random().nextInt(10) < 8){
            log.info("PreAuth approved");
            Message<PaymentEvent> msg = MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                    .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                    .build();
            stateContext.getStateMachine().sendEvent(msg);
        }else{
            log.info("PreAuth declined");
            Message<PaymentEvent> msg = MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                    .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                    .build();
            stateContext.getStateMachine().sendEvent(msg);
        }
    }
}
