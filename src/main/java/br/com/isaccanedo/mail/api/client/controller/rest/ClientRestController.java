package br.com.isaccanedo.mail.api.client.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.isaccanedo.mail.api.client.domain.dto.ClientDTO;
import br.com.isaccanedo.mail.api.client.entity.Client;
import br.com.isaccanedo.mail.api.client.exceptions.ClientAlreadyExistsException;
import br.com.isaccanedo.mail.api.client.exceptions.ClientNotFoundException;
import br.com.isaccanedo.mail.api.client.repository.ClientRepository;
import br.com.isaccanedo.mail.api.client.service.ClientService;
import br.com.isaccanedo.mail.api.client.service.ClientServiceImpl;

/**
 * Controller class with RESTful API endpoint mappings for Client CRUD operations
 * 
 * @see Client
 * @see ClientDTO
 * @see ClientServiceImpl
 * @see ClientService
 * @see ClientRepository
 */
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping(value = "/api/rest/v1/clients")
@RestController
public class ClientRestController {
	@Qualifier("ClientServiceImpl")
	private ClientService clientService;

	@Autowired
	public ClientRestController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getAllClients() {
		List<ClientDTO> clients = this.clientService.getAllClients();
		return ResponseEntity.ok(clients);
	}

	@GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> getClientById(@PathVariable("clientId") Long clientId) {
		try {
			ClientDTO client = this.clientService.getClientById(clientId);
			return ResponseEntity.ok(client);
		} catch (ClientNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createClient(@RequestBody @Valid ClientDTO requestBody, HttpServletRequest request) {
		try {
			String ipAddress = request.getRemoteAddr();
			requestBody.setIpAddress(ipAddress);
			this.clientService.createClient(requestBody);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (ClientAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PutMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateClientById(@PathVariable("clientId") Long clientId, @RequestBody @Valid ClientDTO requestBody) {
		try {
			this.clientService.updateClientById(clientId, requestBody);
			return ResponseEntity.ok().build();
		} catch (ClientNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@DeleteMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> deleteClientById(@PathVariable("clientId") Long clientId) {
		try {
			this.clientService.deleteClientById(clientId);
			return ResponseEntity.ok().build();
		} catch (ClientNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}		
}