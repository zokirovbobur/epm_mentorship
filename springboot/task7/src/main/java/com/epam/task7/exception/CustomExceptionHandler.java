package com.epam.task7.exception;

import com.epam.task7.message.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler({ BadRequestInputException.class })
	public ResponseEntity<ErrorResponse> handelBadRequest(BadRequestInputException exception, HttpServletRequest request) {
		logger.info("In side bad request exception handler method!");
		ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({ UserCustomeException.class })
	public ResponseEntity<ErrorResponse> handelCustomErrorRequest(UserCustomeException exception, HttpServletRequest request) {
		logger.info("In side custome exception handler method!");
		ErrorResponse response = new ErrorResponse(exception.getErrorCode(), exception.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	

}
