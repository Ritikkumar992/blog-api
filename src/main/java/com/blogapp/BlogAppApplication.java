package com.blogapp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapp.config.AppConstants;
import com.blogapp.entities.Role;
import com.blogapp.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("rk-1232"));

		try
		{
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			java.util.List<Role> roles = java.util.List.of(role, role1);

			List<Role>result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
