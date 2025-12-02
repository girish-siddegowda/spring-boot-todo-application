package com.javaworld.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaworld.todo.dto.LoginRequestDTO;
import com.javaworld.todo.dto.LoginResponseDTO;
import com.javaworld.todo.dto.RegisterDTO;
import com.javaworld.todo.dto.RegisterResponseDTO;
import com.javaworld.todo.dto.ResponsePayload;
import com.javaworld.todo.service.impl.AuthServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthServiceImpl authServiceImpl;
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
		
		LoginResponseDTO response = authServiceImpl.authenticateUser(loginRequest);

        return ResponseEntity.ok(
                new ResponsePayload<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        null,                
                        response             
                )
        );
	}

	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO signUpRequest) {
		
		RegisterResponseDTO response = authServiceImpl.registerUser(signUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponsePayload<>(
                        HttpStatus.CREATED.value(),
                        HttpStatus.CREATED.name(),
                        null,
                        response
                )
        );
	}
}
