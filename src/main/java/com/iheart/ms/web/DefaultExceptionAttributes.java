package com.iheart.ms.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class DefaultExceptionAttributes implements ExceptionAttributes {

	@Override
	public Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest,
			HttpStatus httpStatus) {
		
		return null;
	}

}
