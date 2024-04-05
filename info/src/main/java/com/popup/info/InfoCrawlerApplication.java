package com.popup.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import lombok.AllArgsConstructor;

@EnableMongoAuditing
@AllArgsConstructor
@SpringBootApplication
public class InfoCrawlerApplication{

	public static void main(String[] args) {
		SpringApplication.run(InfoCrawlerApplication.class, args);
	}
}