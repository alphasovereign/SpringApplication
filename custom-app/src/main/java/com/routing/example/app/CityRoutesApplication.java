package com.routing.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.routing.example.app")
@EntityScan("com.routing.example.app.model")
@EnableJpaRepositories("com.routing.example.app.repsitory")
public class CityRoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityRoutesApplication.class, args);
	}

}
