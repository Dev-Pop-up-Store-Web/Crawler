package com.popup.info.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.popup.info.domain.PopUpStoreInfo;
import com.popup.info.repository.InfoRepository;
import com.popup.info.service.WebDriverManager;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CrawlerController {

	// 크롤링할 대상 url (가장 상위 페이지)
	private static final String MAIN_PAGE_URL = "https://www.popply.co.kr/popup?area=all";
	private final WebDriver driver = WebDriverManager.initChromeDriver();
	private HashMap<String, String> detailPageUrls = new HashMap<>();

	private final InfoRepository infoRepo;

	// 0. 크롤링 시작
	@PostConstruct
	public void startCrawling() {
		setCrawler();
		getMainPageInfos();
	}

	// 1. 크롤러 객체에 url 할당
	private void setCrawler() {
		driver.get(MAIN_PAGE_URL);
	}

	// 2. 크롤링 할 정보 추출
	private void getMainPageInfos() {
		WebElement container = driver.findElement(By.cssSelector(".inner"));
		setMainPageInfos(container);
	}

	// 3. 데이터 가공
	private void setMainPageInfos(WebElement mainPageInfos) {
		var items = driver.findElements(By.cssSelector(".calendar-popup-list > ul > li"));

		for (int i = 0; i < items.size(); i++) {
			PopUpStoreInfo data = new PopUpStoreInfo();
			WebElement item = items.get(i);

			String href = item.findElement(By.cssSelector("a.popup-img-wrap")).getAttribute("href");
			String id = href.substring(href.lastIndexOf('/') + 1);

			detailPageUrls.put(id, href);

		}
		getDetailUrls();
	}

	// 4. 상세 url 하나씩 접근
	private void getDetailUrls() {
		for (var detailPageUrl : detailPageUrls.entrySet()) {
			driver.get(detailPageUrl.getValue());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Exception!");
			}
			setDetailInfos(detailPageUrl.getKey());
		}
	}

	// 5. 상세 데이터 저장
	private void setDetailInfos(String key) {
		PopUpStoreInfo existingInfo = infoRepo.findById(key).orElse(null);
		if (existingInfo != null) {
			if (existingInfo.getUrls() == null) {
				// image 데이터만 적재
				System.out.println("Already Exists Id..");
				updateInfo(key, existingInfo);
			} else {
				// info 데이터 적재 완료
				System.out.println("Data Already Exists in MongoDB");
				return;
			}
		} else {
			System.out.println("Adding new Id..");
			addNewInfo(key);
		}
	}

	// 5-1. 이미 데이터가 존재하는 경우 업데이트
	private void updateInfo(String key, PopUpStoreInfo existingInfo) {
		setDetailInfos(key, existingInfo);	// 새로운 데이터 추가
		infoRepo.save(existingInfo);	// 기존 데이터 업데이트
	}

	// 5-2. 새로운 데이터 추가
	private void addNewInfo(String key) {
		PopUpStoreInfo newInfo = new PopUpStoreInfo();
		setDetailInfos(key, newInfo);
		infoRepo.insert(newInfo);
	}

	private void setDetailInfos(String key, PopUpStoreInfo popUpStoreInfo) {
		String name = driver.findElement(By.cssSelector(".tit")).getText();

		String[] date = driver.findElement(By.cssSelector(".date")).getText().split(" - ");
		LocalDate startDate = isMatchingDatePattern(date[0]) ? LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yy.MM.dd", Locale.KOREAN)) : null;
		LocalDate endDate = isMatchingDatePattern(date[1]) ? LocalDate.parse(date[1], DateTimeFormatter.ofPattern("yy.MM.dd", Locale.KOREAN)) : null;

		String address = driver.findElement(By.cssSelector(".location")).getText();
		String region = address.split(" ")[1];

		var tags = driver.findElements(By.cssSelector(".popupdetail-title-info > .search-box-inner > ul > li"));
		List<String> hashtags = new ArrayList<>();
		for (int i = 0; i < tags.size(); i++) {
			hashtags.add(tags.get(i).getText());
		}
		popUpStoreInfo.setHashtags(hashtags);

		popUpStoreInfo.setId(key);
		popUpStoreInfo.setName(name);
		popUpStoreInfo.setStartDate(startDate);
		popUpStoreInfo.setEndDate(endDate);
		popUpStoreInfo.setAddress(address);
		popUpStoreInfo.setRegion(region);

		var openHoursElements = driver.findElements(By.cssSelector(".popupdetail-time > ul > li"));
		List<String> openHours = new ArrayList<>();
		for (int i = 1; i < 8; i++) {
			openHours.add(openHoursElements.get(i).getText());
		}
		popUpStoreInfo.setOpenTimes(openHours);

		var liElements = driver.findElements(By.cssSelector(".popupdetail-icon-area > ul > li:not(.false)"));
		for (WebElement liElement : liElements) {
			switch (liElement.getText()) {
				case "주차가능" -> popUpStoreInfo.setParkingAvailability(true);
				case "주차불가" -> popUpStoreInfo.setParkingAvailability(false);
				case "입장료 무료" -> popUpStoreInfo.setTicketPrice(-1);
				case "입장료 유료" -> popUpStoreInfo.setTicketPrice(100000);
			}
		}
	}

	private static boolean isMatchingDatePattern(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd", Locale.KOREAN);
			LocalDate.parse(dateString, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}