package com.blogapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BlogException.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(BlogException io, WebRequest wr){
		System.out.println("inside BlogException..");
		ErrorDetails error = new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(io.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(CommentException io, WebRequest wr){
		System.out.println("inside CommentException..");
		ErrorDetails error = new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(io.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(Exception io, WebRequest wr){
		System.out.println("inside Exception..");
		ErrorDetails error = new ErrorDetails();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(io.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}

}
