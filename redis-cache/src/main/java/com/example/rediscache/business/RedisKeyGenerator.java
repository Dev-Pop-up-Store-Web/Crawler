package com.example.rediscache.business;

import java.time.format.DateTimeFormatter;

import com.example.rediscache.domain.PopUpStoreInfo;

public class RedisKeyGenerator {
	public static String generateKey(PopUpStoreInfo popUpStoreInfo) {
		return popUpStoreInfo.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-"
			+ popUpStoreInfo.getId();
	}
}
