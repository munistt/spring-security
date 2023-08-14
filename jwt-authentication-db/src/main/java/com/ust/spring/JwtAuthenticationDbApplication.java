package com.ust.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthenticationDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationDbApplication.class, args);
		System.out.println("Started...");
	}

}
