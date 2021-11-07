package com.epam.mvc;

import com.epam.mvc.exception.HandlerExceptionToViewResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class MvcApplication {

	@Bean
	public HandlerExceptionResolver customExceptionResolver () {
		return new HandlerExceptionToViewResolver();
	}

	@Bean
	public ViewResolver viewResolver () {
		InternalResourceViewResolver viewResolver =
				new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}

}
