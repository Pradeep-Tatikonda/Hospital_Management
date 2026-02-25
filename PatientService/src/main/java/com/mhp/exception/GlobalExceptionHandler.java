package com.mhp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDuplicateRecord(DataIntegrityViolationException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Record already exists");
	}

}
