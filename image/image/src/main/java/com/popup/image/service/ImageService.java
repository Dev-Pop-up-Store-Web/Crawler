package com.popup.image.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.popup.image.domain.PopUpStoreInfo;
import com.popup.image.repository.InfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	private final InfoRepository infoRepository;

	public void SaveImageUrls(List<String> urls, String id){
		if(infoRepository.findById(id).isPresent()) {
			System.out.println("Already Exists Id.. Adding.... ");
			PopUpStoreInfo PopUpStoreInfoSet = new PopUpStoreInfo();
			PopUpStoreInfoSet = infoRepository.findById(id).get();

			if(PopUpStoreInfoSet.getUrls().isEmpty()){
				System.out.println("MongoDB Image Url is Empty ");
				PopUpStoreInfoSet.setUrls(urls);
				infoRepository.save(PopUpStoreInfoSet);
				System.out.println("MongoDB Image Url Uploaded Completely ");
			}
		}
		else {
			System.out.println("Adding new Id..");
			PopUpStoreInfo popUpStoreInfo = new PopUpStoreInfo();
			popUpStoreInfo.setId(id);
			popUpStoreInfo.setUrls(urls);
			infoRepository.save(popUpStoreInfo);
			System.out.println(" New MongoDB Image Url Uploaded Completely");
		}
	}
}