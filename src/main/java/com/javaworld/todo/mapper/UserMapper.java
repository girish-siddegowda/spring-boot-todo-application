package com.javaworld.todo.mapper;

import org.springframework.stereotype.Component;

import com.javaworld.todo.dto.UserDTO;
import com.javaworld.todo.model.User;

@Component
public class UserMapper {

	public UserDTO convertToDto(User user) {
		
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());      
        dto.setRole(user.getRole());
        dto.setCreatedDate(user.getCreatedDate());

        return dto;
		
	}

}
