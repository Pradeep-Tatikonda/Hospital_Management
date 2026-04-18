package com.mhp.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDuplicateRecord(DataIntegrityViolationException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Record already exists");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage().split(":")[1]);
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException ex ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
