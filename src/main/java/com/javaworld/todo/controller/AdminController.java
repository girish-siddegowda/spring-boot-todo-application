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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AuthServiceImpl authServiceImpl;
	
	@Autowired
    UserServiceImpl userServiceImpl;
	

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
