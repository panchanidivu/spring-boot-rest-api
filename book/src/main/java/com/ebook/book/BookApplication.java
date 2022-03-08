package com.ebook.book;

import java.util.ArrayList;
import java.util.Arrays;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication

public class BookApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
		
	

}
