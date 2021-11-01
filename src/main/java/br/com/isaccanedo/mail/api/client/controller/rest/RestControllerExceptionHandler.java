package br.com.isaccanedo.mail.api.client.controller.rest;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.isaccanedo.mail.api.client.domain.dto.ApiErrorDTO;

/**
 * Component responsible for handling exceptions thrown by Rest Controllers
 */
@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {	
	@ExceptionHandler(value = { ResponseStatusException.class })
	protected ResponseEntity<ApiErrorDTO> handleResponseStatusExceptions(ResponseStatusException ex) {
		logger.error(ex.getMessage(), ex);
		String error = ex.getStatus().getReasonPhrase();
		String message = ex.getReason();
		Integer status = ex.getStatus().value();
		Long timestamp = new Date().getTime();
		ApiErrorDTO body = new ApiErrorDTO(error, message, status, timestamp);
		return ResponseEntity.status(ex.getStatus()).contentType(MediaType.APPLICATION_JSON_UTF8).body(body);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ApiErrorDTO> handleGenericExceptions(Exception ex) {
		logger.error(ex.getMessage(), ex);
		String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		String message = ex.getMessage();
		Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		Long timestamp = new Date().getTime();
		ApiErrorDTO body = new ApiErrorDTO(error, message, status, timestamp);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON_UTF8).body(body);
	}
}
