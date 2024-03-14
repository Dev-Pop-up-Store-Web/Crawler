package com.example.rediscache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@EnableMongoRepositories(basePackages = "com.example.rediscache.repository.mongo")
@Configuration
public class MongoConfig {

	// @Value("${spring.data.mongodb.uri}")
	// private String mongoUri;

	@Value("${spring.data.mongodb.host}")
	private String mongoHost;

	@Value("${spring.data.mongodb.port}")
	private int mongoPort;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(String.format("mongodb://%s:%d", mongoHost, mongoPort));
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), databaseName);
	}
}
