package com.example.practice.demo;

import com.example.practice.demo.entity.User;
import com.example.practice.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	public void run(String... args) throws Exception {
		User user = new User();
		User user2 = new User();
		user.setName("이정엽");
		user2.setName("이지훈");

		userRepository.save(user);
		userRepository.save(user2);
	}

	/**
	 * Thymeleaf Data Dialect
	 */
	@Bean
	public SpringDataDialect springTemplateEngine() {
		return new SpringDataDialect();
	}
}
