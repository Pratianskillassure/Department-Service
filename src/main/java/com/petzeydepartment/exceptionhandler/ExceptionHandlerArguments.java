package com.petzeydepartment.exceptionhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petzeydepartment.exceptions.NoInputException;
import com.petzeydepartment.exceptions.ResourceNotFoundException;
import com.petzeydepartment.serviceimpl.DepartmentServiceImpl;

@RestControllerAdvice
public class ExceptionHandlerArguments{
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerArguments.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException e){
		
		//HashMap<String,String>errorMap=new HashMap<>();
		List<String>errorList=new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(error ->{
			log.error(error.getDefaultMessage());
			errorList.add(error.getDefaultMessage());
		});
		return new ResponseEntity<>(errorList,HttpStatus.BAD_REQUEST);
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> businessException1(ResourceNotFoundException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
//	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoInputException.class)
	public ResponseEntity<?> businessException2(NoInputException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
}
