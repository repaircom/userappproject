package com.userapp.userappproject;

import org.springframework.boot.SpringApplication;

public class TestUserappprojectApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserappprojectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
