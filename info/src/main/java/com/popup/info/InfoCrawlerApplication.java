package com.popup.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.popup.info.service.CrawlerController;

import lombok.AllArgsConstructor;

@EnableMongoAuditing
@AllArgsConstructor
@SpringBootApplication
public class InfoCrawlerApplication{

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(InfoCrawlerApplication.class, args);
		CrawlerController crawlerController = context.getBean(CrawlerController.class);
		crawlerController.startCrawling();
	}

}
