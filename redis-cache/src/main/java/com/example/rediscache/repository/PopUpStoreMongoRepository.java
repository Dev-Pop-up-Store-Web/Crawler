package com.example.rediscache.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.rediscache.domain.PopUpStoreInfo;

public interface PopUpStoreMongoRepository extends MongoRepository<PopUpStoreInfo, String> {
}
