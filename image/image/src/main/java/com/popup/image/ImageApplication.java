package com.popup.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.popup.image.controller.CrawlerController;

import lombok.AllArgsConstructor;

@EnableMongoAuditing
@AllArgsConstructor
@SpringBootApplication
public class ImageApplication {
	public static void main(String[] args) {
		//SpringApplication.run(ImageApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(ImageApplication.class, args);
		CrawlerController crawlerController = context.getBean(CrawlerController.class);
		crawlerController.startCrawling();
	}
}
