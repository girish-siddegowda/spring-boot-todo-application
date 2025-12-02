package com.javaworld.todo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.javaworld.todo.utills.ApiUtils;
@Data
public class RegisterDTO {

    @NotBlank(message = ApiUtils.MANDATORY_FIELD)
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = ApiUtils.NAME_REGEX, message = ApiUtils.INVALID_NAME)
    private String username;

    @NotBlank(message = ApiUtils.MANDATORY_FIELD)
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Email(message = ApiUtils.INVALID_EMAIL)
    private String email;

    @NotBlank(message = ApiUtils.MANDATORY_FIELD)
    @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
    @Pattern(
        regexp = ApiUtils.PASSWOR_RGEX,
        message = ApiUtils.INVALID_PASSWORD
    )
    private String password;
    
}

