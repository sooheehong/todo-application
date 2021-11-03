package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.persistence.TodoRepository;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		if(userRepository.existsByEmail(email)) {
			log.warn("Email alread exists {}", email);
			throw new RuntimeException("Email alread exists");
		}
		
		return userRepository.save(userEntity);
		
	}
	
	public UserEntity getByCreedentials(final String email, final String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

}
