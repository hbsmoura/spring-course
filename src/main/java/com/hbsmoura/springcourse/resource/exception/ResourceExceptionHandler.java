package com.hbsmoura.springcourse.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hbsmoura.springcourse.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<APIError> handleNotFoundException(NotFoundException e) {
		APIError error = new APIError(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<APIError> handleBadCredentialsException(BadCredentialsException e) {
		APIError error = new APIError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> list.add(error.getDefaultMessage()));		
		
		APIErrorList apiErrorList = new APIErrorList(status.value(), "Validation failed", new Date(), list);
		return ResponseEntity.status(status).body(apiErrorList);
	}
}
