package com.mini1.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mini1.exceptions.PlanNotFoundException;

@RestControllerAdvice
public class PlanExceptionHandler 
{
	@ExceptionHandler(value = PlanNotFoundException.class)
	ResponseEntity<ExceptionBean> handleException(PlanNotFoundException pe)
	{
		String message = pe.getMessage();
		ExceptionBean eb=new ExceptionBean();
		eb.setCode("PLAN-ERROR");
		eb.setMessage(message);
		return new ResponseEntity<>(eb,HttpStatus.BAD_REQUEST);
	}
	
}
