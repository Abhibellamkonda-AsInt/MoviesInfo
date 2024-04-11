package com.example.MoviesInfo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoviesInfo.Entity.UserEntity;
import com.example.MoviesInfo.ServiceImpl.UserServiceImpl;
import com.example.MoviesInfo.responseDto.UserRequestDto;
import com.example.MoviesInfo.responseDto.UserResponseDto;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl userservice;
	
	
	
	
	@PostMapping
	public UserResponseDto addUser(@RequestBody UserRequestDto user) {
		return userservice.addUser(user);
	}
	
	@GetMapping
	public List<UserEntity> getUsers(){
		return userservice.getUsers();
	}
	
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable Long id) {
		return userservice.getUserById(id);
	}
	

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		return userservice.deleteUser(id);
	}
	
	@PutMapping
	public UserEntity updateUser(@RequestBody UserEntity user) {
		return userservice.updateUser(user);
	}
}
