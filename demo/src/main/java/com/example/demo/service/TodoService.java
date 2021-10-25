package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		
		repository.save(entity);
		
		TodoEntity saveEntity = repository.findById(entity.getId()).get();
		
		return saveEntity.getTitle();
	}
}
