package com.example.smsProject.exceptionalHandling;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* @ResponseStatus marks a exception class with the status code and reason message that should be returned. 
 * The status code is applied to the HTTP response when the handler method is invoked, 
 * or whenever the specified exception is thrown.*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
