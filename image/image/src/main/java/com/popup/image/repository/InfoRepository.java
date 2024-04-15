package com.popup.image.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.popup.image.domain.PopUpStoreInfo;

public interface InfoRepository extends MongoRepository<PopUpStoreInfo, String> {
}
