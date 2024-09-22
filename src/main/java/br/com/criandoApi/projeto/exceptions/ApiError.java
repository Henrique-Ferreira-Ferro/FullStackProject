package br.com.criandoApi.projeto.exceptions;

import java.time.LocalDateTime;

public class ApiError {
	
	private int statusCode;
	private String message;
	private LocalDateTime date;
	
	public ApiError(int statusCode, String message, LocalDateTime date) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.date = date;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
	
}
