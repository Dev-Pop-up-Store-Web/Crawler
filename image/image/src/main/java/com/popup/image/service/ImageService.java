package com.popup.image.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.popup.image.Domain.Images;
import com.popup.image.Domain.PopUpStoreInfo;
import com.popup.image.repository.PopUpStoreInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	private final PopUpStoreInfoRepository popUpStoreInfoRepository;

	public void SaveImageUrls(List<String> urls, String id){
		if(popUpStoreInfoRepository.findById(id).isPresent()){
			System.out.println("Already Exists Id. Adding.... ");
			PopUpStoreInfo PopUpStoreInfoSet = new PopUpStoreInfo();
			PopUpStoreInfoSet = popUpStoreInfoRepository.findById(id).get();
			PopUpStoreInfoSet.setUrls(urls);
			popUpStoreInfoRepository.save(PopUpStoreInfoSet);
			System.out.println("MongoDB Image Url Uploaded Completely ");
		} else {
			PopUpStoreInfo popUpStoreInfo = new PopUpStoreInfo();
			popUpStoreInfo.setId(id);
			popUpStoreInfo.setUrls(urls);
			popUpStoreInfoRepository.save(popUpStoreInfo);
			System.out.println(" New MongoDB Image Url Uploaded Completely");
		}
	}
}
