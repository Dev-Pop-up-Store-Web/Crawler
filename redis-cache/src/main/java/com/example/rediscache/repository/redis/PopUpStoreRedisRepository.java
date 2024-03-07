package com.example.rediscache.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.example.rediscache.domain.PopUpStoreInfo;

public interface PopUpStoreRedisRepository extends CrudRepository<PopUpStoreInfo, String> {

}
