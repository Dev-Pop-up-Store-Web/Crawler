package com.example.rediscache.business;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rediscache.domain.PopUpStoreInfo;
import com.example.rediscache.repository.mongo.PopUpStoreMongoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DataFetcher {
	private final PopUpStoreMongoRepository popUpStoreMongoRepository;

	public List<PopUpStoreInfo> findDataByModifiedDate() {
		LocalDate date = LocalDate.now();
		return popUpStoreMongoRepository.findByModifiedDate(date);
	}
}
