package com.example.rediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableMongoAuditing
@SpringBootApplication
public class RedisCacheApplication {
	// spring + redis 라즈베리파이 ok
// 1. mongodb -> spring 서버로 데이터 가져오는 코드 작성 ok
// 2. mongodb -> spring -> redis 데이터 잘 적재되는지 테스트 코드 작성 및 확인 ok
// 3. docker compose로 mongodb + spring boot app + redis 묶어서 라파에서 잘 동작하는 지 확인하기
	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}

}
