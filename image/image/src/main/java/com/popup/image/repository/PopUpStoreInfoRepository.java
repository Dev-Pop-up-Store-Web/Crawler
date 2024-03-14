package com.popup.image.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.popup.image.Domain.PopUpStoreInfo;

public interface PopUpStoreInfoRepository extends MongoRepository<PopUpStoreInfo, String> {
}
