package com.javaworld.todo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javaworld.todo.dto.ResponsePayload;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

	    
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(err ->
	            errors.put(err.getField(), err.getDefaultMessage())
	    );

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	            new ResponsePayload<>(
	                    HttpStatus.BAD_REQUEST.value(),
	                    HttpStatus.BAD_REQUEST.name(),
	                    errors,  
	                    null
	            )
	    );
	}

	
	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UsernameNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    	        new ResponsePayload<>(
    	                HttpStatus.NOT_FOUND.value(),
    	                HttpStatus.NOT_FOUND.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
    }
	
	@ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> handleNoteNotFoundException(NoteNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    	        new ResponsePayload<>(
    	                HttpStatus.NOT_FOUND.value(),
    	                HttpStatus.NOT_FOUND.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
    }
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<?> handleRoleNotFound(RoleNotFoundException ex) {
	    
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    	        new ResponsePayload<>(
    	                HttpStatus.NOT_FOUND.value(),
    	                HttpStatus.NOT_FOUND.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
    	        new ResponsePayload<>(
    	                HttpStatus.BAD_REQUEST.value(),
    	                HttpStatus.BAD_REQUEST.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
    }
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex) {
	    
		return ResponseEntity.status(HttpStatus.CONFLICT).body(
    	        new ResponsePayload<>(
    	                HttpStatus.CONFLICT.value(),
    	                HttpStatus.CONFLICT.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
	    
		return ResponseEntity.status(HttpStatus.CONFLICT).body(
    	        new ResponsePayload<>(
    	                HttpStatus.CONFLICT.value(),
    	                HttpStatus.CONFLICT.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleRuntimeException(InvalidCredentialsException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
    	        new ResponsePayload<>(
    	                HttpStatus.BAD_REQUEST.value(),
    	                HttpStatus.BAD_REQUEST.name(),
    	                ex.getMessage(),
    	                null
    	        )
    	);
    }

}
