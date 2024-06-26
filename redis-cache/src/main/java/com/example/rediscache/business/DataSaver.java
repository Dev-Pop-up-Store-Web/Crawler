package com.example.rediscache.business;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.rediscache.domain.PopUpStoreInfo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DataSaver {
	private final RedisTemplate<String, PopUpStoreInfo> redisTemplate;
	private final DataFetcher dataFetcher;

	@Value("${redis.cache.duration.days}")
	private int redisCacheDurationDays;

	@PostConstruct
	public void saveData() {
		List<PopUpStoreInfo> newDataList = dataFetcher.findDataByModifiedDate();
		newDataList.forEach(this::saveDataToRedis);
		System.out.println("Saving Data to Redis is Finished");
	}

	private void saveDataToRedis(PopUpStoreInfo popUpStoreInfo) {
		System.out.println("save Data To Redis !! ID is " + popUpStoreInfo.getId());
		redisTemplate.opsForValue()
			.set(RedisKeyGenerator.generateKey(popUpStoreInfo), popUpStoreInfo,
				Duration.ofDays(redisCacheDurationDays));
	}
}
