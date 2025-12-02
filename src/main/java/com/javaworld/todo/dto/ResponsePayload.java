package com.javaworld.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType     
public record ResponsePayload<T>(

	int statusCode,
	
    String status,
    
    T error,
    
    T data
    
    
){}
