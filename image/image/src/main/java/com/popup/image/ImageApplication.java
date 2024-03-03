package com.popup.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.popup.image.service.CrawlerPopply;
import com.popup.image.service.S3Uploader;

@SpringBootApplication
public class ImageApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ImageApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(CrawlerPopply crawlerPopply) {
		return args -> {
			crawlerPopply.startCrawling();
		};
	}
}
