package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.service.TodoService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
		try {
			UserEntity user = UserEntity.builder()
					.email(userDTO.getEmail())
					.username(userDTO.getUsername())
					.password(userDTO.getPassword())
					.build();
			
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder()
					.email(registeredUser.getEmail())
					.username(registeredUser.getUsername())
					.id(registeredUser.getId())
					.build();

			return ResponseEntity.ok().body(responseUserDTO);
		}
		catch(Exception e) {
			String error = e.getMessage();

			ResponseDTO response = ResponseDTO.<TodoDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
			
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		UserEntity user = userService.getByCreedentials(
				userDTO.getEmail(), userDTO.getPassword());
		
		if (user != null) {
			final UserDTO responseUserDTO = UserDTO.builder()
					.email(user.getEmail())
					.id(user.getId())
					.build();

			return ResponseEntity.ok().body(responseUserDTO);
		}
		else {
			ResponseDTO response = ResponseDTO.<TodoDTO>builder()
					.error("login failed").build();
			return ResponseEntity.badRequest().body(response);
			
		}
	}

}