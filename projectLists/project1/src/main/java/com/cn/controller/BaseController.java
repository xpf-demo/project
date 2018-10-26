package com.cn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BaseController {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void init(HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		request = servletRequest;
		response = servletResponse;
	}
	
	@ExceptionHandler(value={Exception.class})
	public Object hanlderException(Exception e){
		logger.error(e.getMessage());
		return "";
	}
}
