package com.oldmutual.insure.copierdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.oldmutual.insure.copierdemo.controller.config.MyDestinationConfig;
import com.oldmutual.insure.copierdemo.controller.config.MySourceConfig;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

@SpringBootApplication

@Import({MySourceConfig.class, MyDestinationConfig.class})
public class CopierdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopierdemoApplication.class, args);
	}

}
