package com.popup.image.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.popup.image.service.ImageService;
import com.popup.image.service.S3Uploader;
import com.popup.image.service.WebDriverManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlerController {
	private static final String MAIN_PAGE_URL = "https://www.popply.co.kr/popup/";
	private static final Integer numbering = 1360;
	private final WebDriver driver = WebDriverManager.initChromeDriver();
	//private final HashMap<Integer, String> detailPageUrls = new HashMap<>();

	private final S3Uploader s3Uploader;
	private final ImageService imageService;


	// 0. 크롤링 시작
	public void startCrawling() throws IOException, InterruptedException {
		setCrawler();
		getMainPageInfos();
	}

	// 1. 크롤러 객체에 url 할당
	private void setCrawler() {
		// 가장 상위 페이지에서 정보를 가져옴
		for (int i =0; i < 10; i++){
			int adopt_numbering = 0;
			adopt_numbering = numbering + i;
			driver.get(MAIN_PAGE_URL+Integer.toString(adopt_numbering));
		}
	}

	// 2. 크롤링 할 정보 추출
	private void getMainPageInfos() throws IOException, InterruptedException {
		Thread.sleep(2000);
		var mainPageInfos = driver.findElements(By.className("slide-content"));
		setPopUpStoreInfos(mainPageInfos);
	}

	// 3. 데이터 가공
	private void setPopUpStoreInfos(List<WebElement> infos) throws IOException, InterruptedException {
		List<String> urls = new ArrayList<>();
		try {
			for (int i = 0; i < infos.size()-1; i++) {
				String name = infos.get(i)
					.findElement(By.className("slide-img-wrap"))
					.findElement(By.tagName("img"))
					.getAttribute("src");

				urls.add(name);
			}
		} catch(Exception e){
			System.out.println("Exception!");
		}
		setSaveS3ImageUrl(urls);
	}

	// 4. 이미지 S3 저장
	private void setSaveS3ImageUrl(List<String> urls) throws InterruptedException {
		String publicUrl = "";
		List<String> publicUrls = new ArrayList<>();
		for (int i =0; i < 10; i++){
			int adopt_numbering = 0;
			adopt_numbering = numbering + i;
			for (int j = 0; j < urls.size(); j++){
				publicUrl = s3Uploader.downloadAndUploadS3(urls.get(j), adopt_numbering,j);
				publicUrls.add(publicUrl);
				Thread.sleep(2000);
				// 다른 클래스 불러오면서  Cannot invoke "com.popup.image.service.S3Uploader.uploadS3(String)" because "this.s3Uploader" is null
			}
			System.out.println("Start MongoDB Image Url Upload.");
			imageService.SaveImageUrls(publicUrls,Integer.toString(adopt_numbering));
			System.out.println("Finish MongoDB Image Url Uploaded.");
		}
	}
}
