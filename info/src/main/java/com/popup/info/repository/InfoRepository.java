package com.popup.info.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.popupstore.info.PopUpStoreInfo;

@Repository
public interface InfoRepository extends MongoRepository<PopUpStoreInfo, String> {
}
