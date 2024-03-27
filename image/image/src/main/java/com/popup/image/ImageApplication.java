package com.popup.image;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.popup.image.controller.CrawlerController;

@SpringBootApplication
public class ImageApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ImageApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(CrawlerController crawlerController) {
		return args -> {
			crawlerController.startCrawling();
		};
	}
}
