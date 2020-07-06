package com.sample.controller;

import java.awt.PageAttributes.MediaType;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.UserDTO;
import com.sample.model.CreateUserRequestModel;
import com.sample.model.CreateUserResponseModel;
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
	
	@PostMapping(consumes= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE,org.springframework.http.MediaType.APPLICATION_XML_VALUE},
			     produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE,org.springframework.http.MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CreateUserResponseModel> createUser( @Valid @RequestBody CreateUserRequestModel userDetails) {
		ModelMapper mp= new ModelMapper();
		UserDTO userdto= mp.map(userDetails, UserDTO.class);		
		UserDTO createUserDTO = user.createUserDTO(userdto);
		CreateUserResponseModel createUserResponseModel=mp.map(createUserDTO, CreateUserResponseModel.class);
		return  ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseModel);
	}
	
	@GetMapping
	public CreateUserRequestModel getUser() {
		CreateUserRequestModel createUser= new CreateUserRequestModel();
		createUser.setEmail("nausheen.khan@gmail.com");
		createUser.setfName("Nausheen");
		createUser.setLastName("Khan");
		createUser.setPassword("1236");
		
		return createUser;
	}

}
