package com.example.fingryd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.yml")
public class FingrydApplication {
	public static void main(String[] args) {

		SpringApplication.run(FingrydApplication.class, args);
	}

}
