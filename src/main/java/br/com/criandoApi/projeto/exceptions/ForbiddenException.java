package br.com.criandoApi.projeto.exceptions;

public class ForbiddenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ForbiddenException(String message) {
		super(message);
	}
	
}
