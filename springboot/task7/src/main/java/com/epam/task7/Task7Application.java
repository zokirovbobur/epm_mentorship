package com.epam.task7;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0.0", description = "User API doc"))
public class Task7Application {

	public static void main(String[] args) {
		SpringApplication.run(Task7Application.class, args);
	}

}
