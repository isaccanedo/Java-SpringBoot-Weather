package br.com.isaccanedo.mail.api.client.controller.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.isaccanedo.mail.api.client.TestConfig;
import br.com.isaccanedo.mail.api.client.domain.dto.ApiErrorDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class RestControllerExceptionHandlerTest {
	private RestControllerExceptionHandler handler = new RestControllerExceptionHandler();
	
	@Test
	public void handleResponseStatusException_withStatusNotFound() {
		String errorMessage = "Testing the application error handler";
		ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
		ResponseEntity<ApiErrorDTO> response = this.handler.handleResponseStatusExceptions(exception);
		ApiErrorDTO body = response.getBody();
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), body.getStatus().intValue());
		Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), body.getError());
		Assert.assertEquals(errorMessage, body.getMessage());
	}
	
	@Test
	public void handleGenericExceptions_withIllegalStateException() {
		String errorMessage = "Testing the application error handler";
		IllegalStateException exception = new IllegalStateException(errorMessage);
		ResponseEntity<ApiErrorDTO> response = this.handler.handleGenericExceptions(exception);
		ApiErrorDTO body = response.getBody();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.getStatus().intValue());
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), body.getError());
	}
}
