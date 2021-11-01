package br.com.isaccanedo.mail.api.client.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.isaccanedo.mail.api.client.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClientRestControllerIntegrationTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private ClientRestController controller;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String getBaseUrl() {
		return "http://localhost:" + this.port + "/api/rest/v1/clients";
	}
	
	private void setTestRestTemplateContentType(String type) {
		this.restTemplate.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
			request.getHeaders().remove(HttpHeaders.CONTENT_TYPE);
			request.getHeaders().add(HttpHeaders.CONTENT_TYPE, type);
			return execution.execute(request, body);
		}));
	}
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(restTemplate).isNotNull();
		assertThat(port).isGreaterThan(0);
	}
	
	@Test
	public void createClient_WithInvalidName_ShouldReturnErrorStatusBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void createClient_WithInvalidEmail_ShouldReturnErrorStatusBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void createClient_WithInvalidAge_ShouldReturnErrorStatusBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", -1);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void createClient_withExistingEmail_shouldReturnErrorStatusBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "testzzz@email.com");
		jsonObject.addProperty("age", 12);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void createClient_WithValidRequestBody_ShouldReturnStatusCreated() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 12);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_WithValidButNonExistingId_ShouldReturnStatusErrorNotFound() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 12);
		String requestBody = jsonObject.toString();
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 99999L;
		String url = this.getBaseUrl() + "/" + id;
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_WithInvalidName_ShouldReturnErrorStatusBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 12);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 99999L;
		String url = this.getBaseUrl() + "/" + id;
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_WithInvalidEmail_ShouldReturnErrorBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "");
		jsonObject.addProperty("age", 12);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 1L;
		String url = this.getBaseUrl() + "/" + id;
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_WithInvalidAge_ShouldReturnErrorBadRequest() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", -1);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 1L;
		String url = this.getBaseUrl() + "/" + id;
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_withValidIdAndRequestBodyButNonExistingId_ShouldReturnErrorStatusNotFound() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 999999L;
		String url = this.getBaseUrl() + "/" + id;
		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void updateClientById_withValidIdAndRequestBody_ShouldReturnStatusOk() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test22@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		Long id = 1L;
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		String url = this.getBaseUrl() + "/" + id;
		response = this.restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getAllClients_ShouldReturnClientList() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test890890@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		response = this.restTemplate.getForEntity(this.getBaseUrl(), String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		String responseBody = response.getBody();
		Assert.assertNotNull(responseBody);
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(responseBody);
		Assert.assertNotNull(jsonElement);
		Assert.assertTrue(jsonElement.isJsonArray());
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		Assert.assertNotNull(jsonArray);
		Assert.assertTrue(jsonArray.size() >= 1);
	}
	
	@Test
	public void getClientById_WithValidButNonExistingId_ShouldReturnErrorStatusNotFound() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		ResponseEntity<String> response = this.restTemplate.getForEntity(this.getBaseUrl() + "/9999", String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void getClientById_WithValidIdAndExistingId_ShouldReturnOKWithClientInResponseBody() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		response = this.restTemplate.getForEntity(this.getBaseUrl() + "/1", String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void deleteClientById_withValidButNonExistingId_shouldReturnStatusNotFound() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>("", headers);
		
		ResponseEntity<String> response = this.restTemplate.exchange(this.getBaseUrl() + "/99999", HttpMethod.DELETE, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void deleteClientById_withValidId_shouldReturnStatusOk() {
		setTestRestTemplateContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "john doe");
		jsonObject.addProperty("email", "test777@email.com");
		jsonObject.addProperty("age", 18);
		String requestBody = jsonObject.toString();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(this.getBaseUrl(), httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		response = this.restTemplate.exchange(this.getBaseUrl() + "/1", HttpMethod.DELETE, httpEntity, String.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
