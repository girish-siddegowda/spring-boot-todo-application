package com.javaworld.todo.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javaworld.todo.security.services.CustomUserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CustomUserServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// 1. Parse JWT from Authorization header
			String jwt = parseJwt(request);

			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

				// 2. Extract username from JWT
				String username = jwtUtils.getUserNameFromJwtToken(jwt);

				// 3. Load user details
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				// 4. Create Authentication token with authorities
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities() // must include ROLE_ADMIN if admin
				);

				// 5. Set request details (IP, session info)
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// 6. Set authentication in SecurityContext
				SecurityContextHolder.getContext().setAuthentication(authentication);

				logger.debug("User '{}' authenticated with roles: {}", username, userDetails.getAuthorities());
				System.out.println(userDetails.getAuthorities());
			}
		} catch (Exception e) {
			// Log the exception for troubleshooting
			logger.error("Cannot set user authentication: {}", e.getMessage(), e);
		}

		// 7. Continue filter chain
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromHeader(request);
		logger.debug("AuthTokenFilter.java: {}", jwt);
		return jwt;
	}
}
