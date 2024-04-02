package com.example.rediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class RedisCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}

}
