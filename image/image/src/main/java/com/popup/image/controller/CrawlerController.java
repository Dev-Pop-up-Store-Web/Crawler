package com.popup.image.controller;
import java.util.ArrayList;
import java.util.HashMap;
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
	// 크롤링할 대상 url (가장 상위 페이지)
	private static final String MAIN_PAGE_URL = "https://www.popply.co.kr/popup?area=all";
	private final WebDriver driver = WebDriverManager.initChromeDriver();
	private final HashMap<String, String> detailPageUrls = new HashMap<>();

	private final S3Uploader s3Uploader;
	private final ImageService imageService;


	// 0. 크롤링 시작
	public void startCrawling(){
		setCrawler();
		getMainPageInfos();
	}

	// 1. 크롤러 객체에 url 할당
	private void setCrawler() {
		driver.get(MAIN_PAGE_URL);
	}

	// 2. 크롤링 할 정보 추출
	private void getMainPageInfos(){
		WebElement container = driver.findElement(By.cssSelector(".inner"));
		setMainPageInfos(container);
	}

	// 3. 데이터 가공
	private void setMainPageInfos(WebElement mainPageInfos){
		var items = driver.findElements(By.cssSelector(".calendar-popup-list > ul > li"));

		for (int i = 0; i < items.size(); i++) {
			WebElement item = items.get(i);

			String href = item.findElement(By.cssSelector("a.popup-img-wrap")).getAttribute("href");
			String id = href.substring(href.lastIndexOf('/') + 1);

			detailPageUrls.put(id, href);
		}
		getDetailInfos();
	}
	// 4. 상세 url 하나씩 접근
	private void getDetailInfos(){
		for (var detailPageUrl : detailPageUrls.entrySet()) {
			driver.get(detailPageUrl.getValue());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Exception!");
			}
			setDetailInfos(detailPageUrl.getKey());
		}
	}

	// 5. 상세 데이터 저장
	private void setDetailInfos(String key){
		var detailsPageInfos = driver.findElements(By.className("slide-content"));
		String publicUrl = "";
		List<String> publicUrls = new ArrayList<>();
		for (int i = 0; i < detailsPageInfos.size()-1; i++) {
			String name = detailsPageInfos.get(i).findElement(By.className("slide-img-wrap")).findElement(By.tagName("img")).getAttribute("src");

			// s3에 데이터 저장
			publicUrl = s3Uploader.downloadAndUploadS3(name, Integer.parseInt(key),i);
			publicUrls.add(publicUrl);
		}

		// mongoDB에 데이터 저장
		imageService.SaveImageUrls(publicUrls,Integer.toString(Integer.parseInt(key)));
	}
}