package com.javaworld.todo.dto;

public record ResponsePayload<T>(

	int statusCode,
	
    String status,
    
    T error,
    
    T data
    
    
){}
