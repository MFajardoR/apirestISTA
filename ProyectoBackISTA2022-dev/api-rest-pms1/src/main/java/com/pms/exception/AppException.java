package com.pms.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus state;
	private String message;
	
	
	public AppException(HttpStatus state, String message, String additionalMessage) {
		super();
		this.state = state;
		this.message = message;
		this.message = additionalMessage;
	}
	
	

}
