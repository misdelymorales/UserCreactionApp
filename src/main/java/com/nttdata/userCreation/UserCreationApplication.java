package com.nttdata.userCreation;

import com.nttdata.controllers.UserController;
import com.nttdata.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nttdata.repository")
@EntityScan(basePackages = "com.nttdata.models")
@EnableJpaAuditing
@ComponentScan(basePackageClasses = {UserController.class, UserService.class})
public class UserCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCreationApplication.class, args);
	}

}
