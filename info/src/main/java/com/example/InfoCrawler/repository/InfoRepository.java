package com.example.InfoCrawler.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.InfoCrawler.domain.PopUpStoreInfo;

@Repository
public interface InfoRepository extends MongoRepository<PopUpStoreInfo, String> {
}