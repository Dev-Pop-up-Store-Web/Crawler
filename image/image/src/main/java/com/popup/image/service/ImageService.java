package com.popup.image.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popup.image.Domain.Images;
import com.popup.image.repository.ImagesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	private final ImagesRepository imagseRepository;

	// public Integer findImageById(Integer id){
	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	if (imageRepository.findById(String.valueOf(id)) == null){
	// 		System.out.println("can't not find id");
	// 	} else {
	// 		return imageRepository.findById(String.valueOf(id));
	// 	}
	// 	return id;
	// }
	public void SaveImageUrls(List<String> urls, String id){
		if(imagseRepository.findById(id).isPresent()){
			System.out.println("url already exists in mongdb");
		} else {
			Images images = new Images();
			images.setId(id);
			images.setUrls(urls);
			imagseRepository.save(images);
			System.out.println(" MongoDB Image Url Uploaded Completely");
		}
	}
}
