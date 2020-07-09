package com.routing.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.routing.example.app")
public class CityRoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityRoutesApplication.class, args);
	}

}
