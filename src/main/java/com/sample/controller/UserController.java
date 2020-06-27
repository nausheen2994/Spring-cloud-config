package com.sample.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.UserDTO;
import com.sample.model.CreateUserRequestModel;
import com.sample.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private Environment env;
	@Autowired
	UserService user;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port"+env.getProperty("local.server.port");
	}
	
	@PostMapping
	public String createUser( @Valid @RequestBody CreateUserRequestModel userDetails) {
		ModelMapper mp= new ModelMapper();
		UserDTO userdto= mp.map(userDetails, UserDTO.class);
		
		user.createUserDTO(userdto);
		return "User Created";
	}

}
