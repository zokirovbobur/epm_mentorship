package com.epam.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	@GetMapping
	public String getTest(){
		logger.info("hw");
		return "GetTest";
	}
}
