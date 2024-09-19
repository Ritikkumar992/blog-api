package com.blogapp.paylods;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Name must be minimum of 4 character")
	private String name;

	@Email(message = "Email address is not valid !")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 character and max of 10 character")
	private String password;

	@NotEmpty
	private String about;

	private Set<RoleDto> roles = new HashSet<>();
}