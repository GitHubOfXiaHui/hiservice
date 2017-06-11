package com.bupt.hiservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bupt.hiservice.exception.MessageException;

@RestControllerAdvice
public class BaseControllerAdvice {

	@ExceptionHandler(MessageException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handlerMessageException(MessageException e) {
		return e.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handlerException() {
		return "系统忙，请稍后再试！";
	}
	
}
