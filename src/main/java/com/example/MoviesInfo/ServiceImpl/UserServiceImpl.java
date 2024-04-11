package com.example.MoviesInfo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MoviesInfo.Entity.UserEntity;
import com.example.MoviesInfo.Repository.UserRepository;
import com.example.MoviesInfo.responseDto.UserRequestDto;
import com.example.MoviesInfo.responseDto.UserResponseDto;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl {
	@Autowired
	private UserRepository userrepository;
	
	public UserResponseDto addUser(UserRequestDto user) {
		Long id =user.getId();
		if(id != null)
		{
			Optional<UserEntity> found= userrepository.findById(id);
			if(found.isPresent()) {
				throw new IllegalStateException(id+"already exits");
			}
		}
		UserEntity usere = new UserEntity();
		usere.setName(user.getName());
		UserEntity saveduser = userrepository.save(usere);
		UserResponseDto us = new UserResponseDto();
		us.setId(saveduser.getId());
		us.setName(saveduser.getName());
		return us;
	}
	
	public List<UserEntity> getUsers(){
		return userrepository.findAll();
	}
	
	public Optional<UserEntity> getUserById(Long id ){
		return userrepository.findById(id);
	}
	
	public String deleteUser(Long id) {
		UserEntity user=userrepository.findById(id).orElse(null);
		if(user!=null) {
			userrepository.delete(user);
			return "user deleted";
		}
		return "No User Found";
		
	}
	
	public UserEntity updateUser(UserEntity user) {
		return userrepository.save(user);
	}
	
	
		
}
