package com.personal.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {

	// TODO: Verificar maneira de retornar sempre o mesmo DTO quando houver erro nos endpoints

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
