package com.example.rediscache.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.rediscache.domain.PopUpStoreInfo;

public interface PopUpStoreRedisRepository extends CrudRepository<PopUpStoreInfo, String> {

}
