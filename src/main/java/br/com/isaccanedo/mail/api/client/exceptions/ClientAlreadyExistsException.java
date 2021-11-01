package br.com.isaccanedo.mail.api.client.exceptions;

public class ClientAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -2985381180760118718L;
	
	public ClientAlreadyExistsException(String message) {
		super(message);
	}
}
