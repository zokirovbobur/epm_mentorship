package com.epam.task7.exception;

public class BadRequestInputException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public BadRequestInputException() {
		super();
	}
	
	public BadRequestInputException(final String message) {
		super(message);
	}
	
	public BadRequestInputException(final int pErrorCode, final String pMessage) {
		super(pMessage);
		this.errorCode = pErrorCode;
	}
	
	public BadRequestInputException(final int pErrorCode, final String pMessage, final Throwable throwable) {
		super(pMessage, throwable);
		this.errorCode = pErrorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
