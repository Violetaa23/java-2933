package com.uberits.uberitspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan("com.uberits.entidades")
@SpringBootApplication
public class UberitspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberitspringApplication.class, args);
	}

}
