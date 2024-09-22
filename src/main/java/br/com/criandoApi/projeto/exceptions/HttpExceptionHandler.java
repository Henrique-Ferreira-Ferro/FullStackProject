package br.com.criandoApi.projeto.exceptions;

import java.time.LocalDateTime;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler {
	
	private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message){
		var error = new ApiError(status.value(), message, LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> badRequest(BadRequestException exception){
		return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ApiError> Forbidden(ForbiddenException exception){
		return buildErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
	}
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ApiError> notFound(ObjectNotFoundException exception){
		return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> userNotFound(UserNotFoundException exception){
		return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	
}
