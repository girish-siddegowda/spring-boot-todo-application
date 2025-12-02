package com.javaworld.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestDTO {

	@Schema(
	        description = "Username of the user attempting to log in",
	        example = "john_doe",
	        required = true
	    )
	private String username;
	
	@Schema(
	        description = "Password of the user",
	        example = "John@1234",
	        required = true
	    )
    private String password;
}
