package com.oldmutual.insure.copierdemo.controller.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;

@Configuration
public class MyDestinationConfig {

    @Bean
    MyDestination myDestination() {
        return new MyDestination();
    }
}