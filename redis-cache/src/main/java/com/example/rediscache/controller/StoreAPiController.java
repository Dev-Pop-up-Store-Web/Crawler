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
import com.example.rediscache.repository.PopUpStoreInfoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreAPiController {
	private final PopUpStoreInfoRepository popUpStoreInfoRepository;

	@GetMapping
	Iterable<PopUpStoreInfo> getStoresInfo() {
		return popUpStoreInfoRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<PopUpStoreInfo> getStoreById(@PathVariable Long id) {
		return popUpStoreInfoRepository.findById(id);
	}

	@PostMapping
	PopUpStoreInfo postStoreInfo(@RequestBody PopUpStoreInfo storeInfo) {
		return popUpStoreInfoRepository.save(storeInfo);
	}

	@DeleteMapping("{id}")
	void deleteStoreInfo(@PathVariable Long id) {
		popUpStoreInfoRepository.deleteById(id);
	}
}
