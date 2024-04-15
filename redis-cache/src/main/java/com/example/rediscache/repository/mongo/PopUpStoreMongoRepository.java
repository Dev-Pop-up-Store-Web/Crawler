package com.example.rediscache.repository.mongo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.rediscache.domain.PopUpStoreInfo;

public interface PopUpStoreMongoRepository extends MongoRepository<PopUpStoreInfo, String> {
	List<PopUpStoreInfo> findByModifiedDate(LocalDate yourDateField);
}
