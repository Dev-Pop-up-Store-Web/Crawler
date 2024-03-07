package com.example.rediscache.business;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.rediscache.domain.PopUpStoreInfo;
import com.example.rediscache.repository.mongo.PopUpStoreMongoRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataFetcher {
	private final PopUpStoreMongoRepository popUpStoreMongoRepository;

	public List<PopUpStoreInfo> findDataByDateField() {
		LocalDate date = LocalDate.now();
		return popUpStoreMongoRepository.findByUpdatedDate(date);
	}
}
