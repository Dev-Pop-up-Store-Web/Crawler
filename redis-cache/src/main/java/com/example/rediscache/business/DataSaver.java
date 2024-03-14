package com.example.rediscache.business;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.rediscache.domain.PopUpStoreInfo;
import com.example.rediscache.repository.redis.PopUpStoreRedisRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class DataSaver {
	private final PopUpStoreRedisRepository popUpStoreRedisRepository;
	private final DataFetcher dataFetcher;

	// @Scheduled(cron = "0 0 * * * *") // 매일 정각에 한 번 실행
	@Scheduled(cron = "10 * * * * *")
	public void saveData() {
		var newData = dataFetcher.findDataByModifiedDate();
		popUpStoreRedisRepository.saveAll(newData);
	}
}
