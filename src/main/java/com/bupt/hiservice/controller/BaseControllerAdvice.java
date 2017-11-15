package com.bupt.hiservice.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;

//@ControllerAdvice
public class BaseControllerAdvice {

	/*@ExceptionHandler(Exception.class)
	public @ResponseBody BaseResponseDTO handlerException() throws Exception {
		return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_20, BaseResponseDTO.ResponseDTO.class);
	}*/
	
}
