package com.javaworld.todo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaworld.todo.dto.LoginRequestDTO;
import com.javaworld.todo.dto.LoginResponseDTO;
import com.javaworld.todo.dto.RegisterDTO;
import com.javaworld.todo.dto.RegisterResponseDTO;
import com.javaworld.todo.exception.EmailAlreadyExistsException;
import com.javaworld.todo.exception.InvalidCredentialsException;
import com.javaworld.todo.exception.RoleNotFoundException;
import com.javaworld.todo.exception.UserAlreadyExistsException;
import com.javaworld.todo.jwt.JwtUtils;
import com.javaworld.todo.model.AppRole;
import com.javaworld.todo.model.Role;
import com.javaworld.todo.model.User;
import com.javaworld.todo.repositories.RoleRepository;
import com.javaworld.todo.repositories.UserRepository;
import com.javaworld.todo.service.AuthService;
import com.javaworld.todo.utills.ApiUtils;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository rolerepository;

	@Autowired
	UserRepository useRepository;

	@Override
	public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest) {

		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());


			return LoginResponseDTO.builder().username(userDetails.getUsername()).jwtToken(jwtToken).roles(roles)
					.build();
			
		} catch (BadCredentialsException ex) {

			throw new InvalidCredentialsException(ApiUtils.BAD_CREDENTIALS);
		}
	}

	@Override
	public RegisterResponseDTO registerUser(RegisterDTO signUpRequest) {

		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			throw new UserAlreadyExistsException(ApiUtils.USER_ALREADY_EXISTS);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new EmailAlreadyExistsException(ApiUtils.EMAIL_ALREADY_EXISTS);
		}

		Role role = rolerepository.findByRoleName(AppRole.ROLE_USER)
				.orElseThrow(() -> new RoleNotFoundException(ApiUtils.ROLE_NOT_FOUND));

		User user = User.builder().userName(signUpRequest.getUsername()).email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword())).role(role).accountNonLocked(true)
				.accountNonExpired(true).credentialsNonExpired(true).enabled(true)
				.credentialsExpiryDate(LocalDate.now().plusYears(1)).accountExpiryDate(LocalDate.now().plusYears(1))
				.isTwoFactorEnabled(false).signUpMethod("email").build();

		User use = userRepository.save(user);

		return RegisterResponseDTO.builder().username(use.getUserName()).email(use.getEmail()).build();
	}

	@Override
	public RegisterResponseDTO registeradmin(RegisterDTO signUpRequest) {

		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			throw new UserAlreadyExistsException(ApiUtils.USER_ALREADY_EXISTS);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new EmailAlreadyExistsException(ApiUtils.EMAIL_ALREADY_EXISTS);
		}

		Role role = rolerepository.findByRoleName(AppRole.ROLE_ADMIN)
				.orElseThrow(() -> new RoleNotFoundException(ApiUtils.ROLE_NOT_FOUND));

		User user = User.builder().userName(signUpRequest.getUsername()).email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword())).role(role).accountNonLocked(true)
				.accountNonExpired(true).credentialsNonExpired(true).enabled(true)
				.credentialsExpiryDate(LocalDate.now().plusYears(1)).accountExpiryDate(LocalDate.now().plusYears(1))
				.isTwoFactorEnabled(false).signUpMethod("email").build();

		User AdminUser = userRepository.save(user);

		return RegisterResponseDTO.builder().username(AdminUser.getUserName()).email(AdminUser.getEmail()).build();
	}

}
