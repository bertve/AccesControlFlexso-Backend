package com.springBoot.keyAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import domain.Company;
import service.*;

@SpringBootApplication
public class KeyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyApiApplication.class, args);
		
	}
	
	@Bean
	public CompanyDao companyDao() {
		return new JpaCompanyDao();
	}
	
	@Bean
	public OfficeDao officeDao() {
		return new JpaOfficeDao();
	}
	
	@Bean
	public AuthorizedPersonDao authorizedPersonDao() {
		return new JpaAuthorizedPersonDao();
	}

}
