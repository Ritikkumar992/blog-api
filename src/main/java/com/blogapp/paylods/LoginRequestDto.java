package com.blogapp.paylods;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
	private String email;
	private String password;
}
