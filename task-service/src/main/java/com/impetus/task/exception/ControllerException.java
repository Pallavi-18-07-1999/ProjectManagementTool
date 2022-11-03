package com.impetus.task.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.impetus.task.model.ResponseModel;

import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
public class ControllerException extends ResponseEntityExceptionHandler{
	
	 @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
       
      
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        log.error(errors.toString());
        ResponseModel<String> responseModel=new ResponseModel<>();
        responseModel.setException(errors.toString());
        
        return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
	}
	 
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<ResponseModel<String>> handleDaoException(DaoException daoException)
	{
		log.error(daoException.getMessage());
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setException(daoException.getMessage());
		return new ResponseEntity<>(responseModel,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModel<String>> handleException(Exception e) {
		log.error(e.getMessage());
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setException(e.getMessage());
		return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
