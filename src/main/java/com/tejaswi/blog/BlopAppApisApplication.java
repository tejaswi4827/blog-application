package com.tejaswi.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class BlopAppApisApplication {
	@Bean
	ModelMapper ModelMapper() {
		return new ModelMapper();
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlopAppApisApplication.class, args);
	}

	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("xyz"));
	}
}
