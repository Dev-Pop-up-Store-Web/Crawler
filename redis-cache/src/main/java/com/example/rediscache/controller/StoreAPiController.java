package com.example.rediscache.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rediscache.domain.PopUpStoreInfo;
import com.example.rediscache.repository.redis.PopUpStoreRedisRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreAPiController {
	private final PopUpStoreRedisRepository popUpStoreRedisRepository;

	@GetMapping
	Iterable<PopUpStoreInfo> getStoresInfo() {
		return popUpStoreRedisRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<PopUpStoreInfo> getStoreById(@PathVariable String id) {
		return popUpStoreRedisRepository.findById(id);
	}

	@PostMapping
	PopUpStoreInfo postStoreInfo(@RequestBody PopUpStoreInfo storeInfo) {
		return popUpStoreRedisRepository.save(storeInfo);
	}

	@DeleteMapping("{id}")
	void deleteStoreInfo(@PathVariable String id) {
		popUpStoreRedisRepository.deleteById(id);
	}
}
