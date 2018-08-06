package com.iheart.ms.web.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iheart.ms.exceptions.NoResultException;
import com.iheart.ms.web.DefaultExceptionAttributes;
import com.iheart.ms.web.ExceptionAttributes;

public class BaseController {
	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
    * Handles  NoResultExceptions thrown from  controller methods.
    * 
    * @param noResultException A NoResultException instance.
    * @return A ResponseEntity containing the Exception Attributes in the body
    *         and HTTP status code 404.
    */
   @ExceptionHandler(NoResultException.class)
   public ResponseEntity<Map<String, Object>> handleNoResultException(
           NoResultException noResultException, HttpServletRequest request) {

       logger.info("> handleNoResultException");

       ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();

       Map<String, Object> responseBody = exceptionAttributes
               .getExceptionAttributes(noResultException, request,
                       HttpStatus.NOT_FOUND);

       logger.info("< handleNoResultException");
       return new ResponseEntity<Map<String, Object>>(responseBody,
               HttpStatus.NOT_FOUND);
   }

  
}
