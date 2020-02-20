package com.springBoot.keyAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication
public class KeyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyApiApplication.class, args);		
	}
	
	
}
