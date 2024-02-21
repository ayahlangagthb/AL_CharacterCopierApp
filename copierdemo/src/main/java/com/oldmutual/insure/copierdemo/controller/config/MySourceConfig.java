package com.oldmutual.insure.copierdemo.controller.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

@Configuration
public class MySourceConfig {

    @Bean
    MySource mySource() {
        return new MySource("mockSource");
    }
}

