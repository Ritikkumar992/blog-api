package com.blogapp.services;

import java.util.List;

import com.blogapp.paylods.UserDto;

public interface UserService {

	// registerNew user having some role defined.
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

	//UserDto loginUser(String email, String password) throws InvalidCredentialsException;
}
