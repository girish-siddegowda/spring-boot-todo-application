package com.javaworld.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaworld.todo.dto.UserDTO;
import com.javaworld.todo.exception.RoleNotFoundException;
import com.javaworld.todo.exception.UserNotFoundException;
import com.javaworld.todo.mapper.UserMapper;
import com.javaworld.todo.model.AppRole;
import com.javaworld.todo.model.Role;
import com.javaworld.todo.model.User;
import com.javaworld.todo.repositories.RoleRepository;
import com.javaworld.todo.repositories.UserRepository;
import com.javaworld.todo.service.UserService;
import com.javaworld.todo.utills.ApiUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserMapper userMapper;
    
	@Override
	public void updateUserRole(Long userId, String roleName) {
		
		User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException(ApiUtils.USER_NOT_FOUND));
		
        AppRole appRole = AppRole.valueOf(roleName);
        
        Role role = roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RoleNotFoundException(ApiUtils.ROLE_NOT_FOUND));
        user.setRole(role);
        
        userRepository.save(user);

	}

	@Override
	public List<UserDTO> getAllUsers() {
		 List<User> users= userRepository.findAll();
		 
		 if (users.isEmpty()) {
		        throw new UserNotFoundException(ApiUtils.USER_NOT_FOUND);
		 }
		 
		 return users.stream()
		            .map(userMapper::convertToDto)
		            .collect(Collectors.toList());				 
	}

	@Override
	public UserDTO getUserById(Long id) {
		  
	      User user = userRepository.findById(id).orElseThrow(()
	                -> new UserNotFoundException(ApiUtils.USER_NOT_FOUND));
	      
	      return userMapper.convertToDto(user);
	}

}
