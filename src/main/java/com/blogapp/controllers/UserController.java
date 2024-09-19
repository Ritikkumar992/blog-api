package com.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.paylods.UserDto;
import com.blogapp.services.UserService;
import com.blogapp.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserService userService;

	// POST -> create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto usrDto)
	{
		UserDto createUserDto = this.userService.createUser(usrDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	//PUT -> update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// ADMIN can only delete user.

	//DELETE -> delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

	// GET -> get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	// Get -> get a single user.
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	//	@PostMapping("/login")
	//	public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequestDto loginRequest) {
	//		UserDto userDto = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
	//		return new ResponseEntity<>(userDto, HttpStatus.OK);
	//	}

}
