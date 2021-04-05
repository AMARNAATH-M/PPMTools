package io.agileintellligence.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class FullstackApplication {

	@Bean
	BCryptPasswordEncoder  bcryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(FullstackApplication.class, args);

	}

}
