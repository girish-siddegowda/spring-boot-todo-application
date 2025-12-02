package com.javaworld.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaworld.todo.dto.RegisterDTO;
import com.javaworld.todo.dto.RegisterResponseDTO;
import com.javaworld.todo.dto.ResponsePayload;
import com.javaworld.todo.dto.UserDTO;
import com.javaworld.todo.service.impl.AuthServiceImpl;
import com.javaworld.todo.service.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin API", description = "APIs for admin operations")
public class AdminController {

	@Autowired
	AuthServiceImpl authServiceImpl;
	
	@Autowired
    UserServiceImpl userServiceImpl;
	
	@Operation(summary = "Register a new admin user",
            responses = {
                @ApiResponse(responseCode = "201", description = "Admin user created successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponsePayload.class))),
                @ApiResponse(responseCode = "400", description = "Invalid request")
            })
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO signUpRequest) {
		
		RegisterResponseDTO user=authServiceImpl.registeradmin(signUpRequest);
		
		return ResponseEntity.ok(
    	        new ResponsePayload<>(
    	                HttpStatus.CREATED.value(),
    	                HttpStatus.CREATED.name(),
    	                null,
    	                user
    	        )
    	);
	}
	
	 @Operation(summary = "Get all users",
             responses = {
                 @ApiResponse(responseCode = "200", description = "List of users",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = ResponsePayload.class))),
                 @ApiResponse(responseCode = "404", description = "No users found")
             })
    @GetMapping("/getusers")
    public ResponseEntity<ResponsePayload<?>> getAllUsers() {
    	
    	List<UserDTO> usersList= userServiceImpl.getAllUsers();
    	
    	return ResponseEntity.ok(
    	        new ResponsePayload<>(
    	                HttpStatus.OK.value(),
    	                HttpStatus.OK.name(),
    	                null,
    	                usersList
    	        )
    	);
      
    }

	 @Operation(summary = "Update user role",
             responses = {
                 @ApiResponse(responseCode = "200", description = "User role updated successfully"),
                 @ApiResponse(responseCode = "404", description = "User not found")
             })	 
    @PutMapping("/update-role")
    public ResponseEntity<?> updateUserRole(@RequestParam Long userId, 
                                                 @RequestParam String roleName) {
    	userServiceImpl.updateUserRole(userId, roleName);
        
    	return ResponseEntity.ok(
    	        new ResponsePayload<>(
    	                HttpStatus.OK.value(),
    	                HttpStatus.OK.name(),
    	                null,
    	                "User role updated successfully"
    	        )
    	);

    }

	 @Operation(summary = "Get a user by ID",
             responses = {
                 @ApiResponse(responseCode = "200", description = "User details",
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = ResponsePayload.class))),
                 @ApiResponse(responseCode = "404", description = "User not found")
             })
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
    	UserDTO user=userServiceImpl.getUserById(id);
        
        return ResponseEntity.ok(
    	        new ResponsePayload<>(
    	                HttpStatus.OK.value(),
    	                HttpStatus.OK.name(),
    	                null,
    	                user
    	        )
    	);
    }
}
