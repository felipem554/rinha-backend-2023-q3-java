package com.hugomarques.rinhabackend2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hugomarques.rinhabackend2023.*")
public class RinhaBackend2023Application {


	public static void main(String[] args) {
		SpringApplication.run(RinhaBackend2023Application.class, args);
	}

}