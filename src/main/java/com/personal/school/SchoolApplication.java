package com.personal.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SchoolApplication {

	// TODO: Token expirar e atualizar token
	// TODO: Adicionar LOG

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
