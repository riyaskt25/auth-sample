package com.lendo.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lendo.auth.controller.response.Error;
import com.lendo.auth.controller.response.ResponseWrapper;
import com.lendo.auth.exception.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ResponseWrapper> handleIAE(Exception exp) {
		LOGGER.debug("Inside GlobalExceptionHandler handleIAE");
		return new ResponseEntity<>(handleLocalizedException(exp.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.OK);
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<ResponseWrapper> handleCE(Exception exp) {
		LOGGER.debug("Inside GlobalExceptionHandler handleCE");
		return new ResponseEntity<>(handleException(HttpStatus.UNAUTHORIZED, exp), HttpStatus.OK);
	}

	private ResponseWrapper handleLocalizedException(String localized, HttpStatus httpStatus) {
		LOGGER.info("Inside GlobalExceptionHandler handleLocalizedException");
		ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();
		
		Error.ErrorBuilder errorBuilder = Error.builder();
		errorBuilder.cause(httpStatus.getReasonPhrase()).errorCode(httpStatus.value()).description(localized);
		
		return responseWrapperBuilder.error(errorBuilder.build()).build();
	}

	private ResponseWrapper handleException(HttpStatus httpStatus, Exception exception) {
		LOGGER.info("Inside GlobalExceptionHandler handleException");
		ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();
		Error.ErrorBuilder errorBuilder = Error.builder();

		errorBuilder.cause(httpStatus.getReasonPhrase()).errorCode(httpStatus.value())
				.description(exception.getMessage());
		return responseWrapperBuilder.error(errorBuilder.build()).build();
	}

}
