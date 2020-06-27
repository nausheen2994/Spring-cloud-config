package com.sample.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.data.UserEntity;
import com.sample.data.UserRepository;
import com.sample.dto.UserDTO;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDTO createUserDTO(UserDTO userDTO) {
		userDTO.setUserId(UUID.randomUUID().toString());
		 ModelMapper model= new ModelMapper();
		 model.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		 UserEntity userMap = model.map(userDTO, UserEntity.class);
		 userMap.setEncryptedPassword("Test");
		 userRepository.save(userMap);
		 return null;
	}

}
