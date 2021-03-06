package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handle specific exception

	@ExceptionHandler
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// handle specific exception

	@ExceptionHandler
	public ResponseEntity<?> handleAPIException(APIException apiException,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), apiException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handle global exception
	
	@ExceptionHandler
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	//handle custom validation 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException exception){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Error", 
				exception.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
