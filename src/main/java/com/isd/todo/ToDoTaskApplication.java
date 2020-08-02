package com.isd.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.isd.todo")
public class ToDoTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoTaskApplication.class, args);
	}

}
