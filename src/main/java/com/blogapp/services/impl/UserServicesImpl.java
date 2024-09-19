package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp.config.AppConstants;
import com.blogapp.entities.Role;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.paylods.UserDto;
import com.blogapp.repositories.RoleRepo;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;


@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserRepo userRepo;


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);

		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		this.userRepo.save(user); // to save the update of the user in userRepo.

		return this.userToDto(user); // return the updated UserDto
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	//	@Override
	//	public UserDto loginUser(String email, String password) throws InvalidCredentialsException {
	//		User user = userRepo.findByEmail(email)
	//				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or password!"));
	//
	//		if (passwordEncoder.matches(password, user.getPassword())) {
	//			return modelMapper.map(user, UserDto.class);
	//		} else {
	//			throw new InvalidCredentialsException("Invalid email or password!");
	//		}
	//	}
	//

	public User dtoToUser(UserDto userDto) {
		//		User user  = new User();
		//
		//		user.setId(userDto.getId());
		//		user.setName(userDto.getName());
		//		user.setEmail(userDto.getEmail());
		//		user.setPasssword(userDto.getPassword());
		//		user.setAbout(userDto.getAbout());
		//
		//		return user;

		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user =this.modelMapper.map(userDto, User.class);

		// we have encoded the password.
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// setting roles to new user.

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

}
