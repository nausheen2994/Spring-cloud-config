package com.sample.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sample.dto.UserDTO;

public interface UserService extends UserDetailsService {

	
	UserDTO createUserDTO (UserDTO userDTO);
	UserDTO getUserDTO(String userName);
}
