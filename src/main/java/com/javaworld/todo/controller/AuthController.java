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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for User Login & Registration")
public class AuthController {
	
	@Autowired
	AuthServiceImpl authServiceImpl;
	
	@Operation(
			summary = "User Login",
			description = "Authenticates user credentials and returns a JWT token.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Successfully authenticated",
							content = @Content(
									schema = @Schema(implementation = ResponsePayload.class)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Invalid login request",
							content = @Content(schema = @Schema(hidden = true))
					),
					@ApiResponse(
							responseCode = "401",
							description = "Unauthorized (invalid credentials)",
							content = @Content(schema = @Schema(hidden = true))
					)
			}
	)
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
	
	@Operation(
			summary = "User Registration",
			description = "Creates a new user account with username, email, and password.",
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "User created successfully",
							content = @Content(
									schema = @Schema(implementation = ResponsePayload.class)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Validation error",
							content = @Content(schema = @Schema(hidden = true))
					),
					@ApiResponse(
							responseCode = "409",
							description = "User already exists / email already used",
							content = @Content(schema = @Schema(hidden = true))
					)
			}
	)
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
