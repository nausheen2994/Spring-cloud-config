package com.sample.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.dto.UserDTO;
import com.sample.model.LoginRequestModel;
import com.sample.service.UserService;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	private Environment env;
	
	 public AuthenticationFilter(UserService userService,Environment env,AuthenticationManager authManager) {
		 this.userService=userService;
		 this.env=env;
		 super.setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequestModel loginRequestModel = new ObjectMapper().readValue(request.getInputStream(),
					LoginRequestModel.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					loginRequestModel.getEmail(), loginRequestModel.getPassword(), new ArrayList<>()));

		} catch (IOException e) {

			throw new RuntimeException();
		}

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			                                HttpServletResponse res,FilterChain chain,Authentication auth) {
		
		String userName=((User) auth.getPrincipal()).getUsername();
        UserDTO userDto= userService.getUserDTO(userName);
        String token=Jwts.builder()
        		     .setSubject(userDto.getUserId()).
        		     setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token_expires")))).
        		     signWith(SignatureAlgorithm.HS512, env.getProperty("secret_key")).compact();
        res.addHeader("Token", token);
        res.addHeader("UserId", userDto.getUserId()	);
        		    
	}

}
