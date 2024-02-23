package com.oldmutual.insure.copierdemo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oldmutual.insure.copierdemo.configs.implementation.SimpleIDestination;
import com.oldmutual.insure.copierdemo.configs.implementation.SimpleISource;
import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.interfaces.ISource;

@Configuration
public class AppConfig {
	/*
	 *  examples of configs for bean building
	 */	  @Bean
	    ISource simpleISource() {
		  return new SimpleISource("Ayanda",0);   
	       }
	
	  @Bean
	     IDestination simpleIDestination() {
		  return new SimpleIDestination();
	     
	    }
}