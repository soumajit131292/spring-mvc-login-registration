package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.bridgelabz.controller")
public class ExceptionHandling {

	@ExceptionHandler
	public String exceptionHandling(Exception exception)
	{
		return "error";
	}
}