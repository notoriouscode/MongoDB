package com.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithMongoDbApplication {

	public static void main(String[] args) {
		System.out.println("SpringBootWithMongoDbApplication.main()");
		SpringApplication.run(SpringBootWithMongoDbApplication.class, args);
	}
}
