package br.com.isaccanedo.mail.api.client.exceptions;

/**
 * Exception which occurs when a Client was not found
 * @see Client
 */
public class ClientNotFoundException extends Exception {
	private static final long serialVersionUID = -1362146675239403208L;

	private static final String MESSAGE = "Client with id '%d' not found";
	
	public ClientNotFoundException(Long clientId) {
		super(String.format(MESSAGE, clientId));
	}
}
