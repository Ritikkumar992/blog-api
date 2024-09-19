package com.blogapp.paylods;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	private String password;

}
