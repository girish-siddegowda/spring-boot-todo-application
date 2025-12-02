package com.javaworld.todo.service;

import com.javaworld.todo.dto.LoginRequestDTO;
import com.javaworld.todo.dto.LoginResponseDTO;
import com.javaworld.todo.dto.RegisterDTO;
import com.javaworld.todo.dto.RegisterResponseDTO;

public interface AuthService {

	LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest);
	RegisterResponseDTO registerUser(RegisterDTO signUpRequest);
	RegisterResponseDTO registeradmin(RegisterDTO signUpRequest);
}
