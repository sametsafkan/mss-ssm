package com.sametsafkan.mssssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachineFactory;

@EnableStateMachineFactory
@SpringBootApplication
public class MssSsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MssSsmApplication.class, args);
    }

}

