package com.javaworld.todo.service;

import java.util.List;

import com.javaworld.todo.dto.UserDTO;

public interface UserService {

	void updateUserRole(Long userId, String roleName);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);
}
