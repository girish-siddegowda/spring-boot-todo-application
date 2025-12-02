package com.javaworld.todo.dto;

import java.time.LocalDateTime;

import com.javaworld.todo.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
}
