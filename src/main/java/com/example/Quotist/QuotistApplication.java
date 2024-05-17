package com.example.Quotist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Quotist API", version = "1.0", description = "API for managing and retrieving quotes"))
public class QuotistApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotistApplication.class, args);
	}
}
