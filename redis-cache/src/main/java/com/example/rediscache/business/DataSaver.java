package com.example.rediscache.business;


import org.springframework.stereotype.Component;

import com.example.rediscache.repository.redis.PopUpStoreRedisRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
class DataSaver {
	private final PopUpStoreRedisRepository popUpStoreRedisRepository;
	private final DataFetcher dataFetcher;

	@PostConstruct
	public void saveData() {
		var newData = dataFetcher.findDataByModifiedDate();
		popUpStoreRedisRepository.saveAll(newData);
	}
}
