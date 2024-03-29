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
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.popup.info.domain.PopUpStoreInfo;
	import com.popup.info.repository.InfoRepository;
	import com.popup.info.service.WebDriverManager;

	@Service
	public class CrawlerController {

		// 크롤링할 대상 url (가장 상위 페이지)
		private static final String MAIN_PAGE_URL = "https://www.popply.co.kr/popup?area=all";
		private WebDriver driver = WebDriverManager.initChromeDriver();
		private HashMap<Integer, PopUpStoreInfo> popUpStoreInfos = new HashMap<>();
		private HashMap<Integer, String> detailPageUrls = new HashMap<>();

		@Autowired
		private InfoRepository infoRepo;

		// 0. 크롤링 시작
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

				String[] date = item.findElement(By.cssSelector(".popup-date")).getText().split(" - ");
				LocalDate startDate = isMatchingDatePattern(date[0]) ? LocalDate.parse(date[0], DateTimeFormatter.ofPattern("yy.MM.dd", Locale.KOREAN)) : null;
				LocalDate endDate = isMatchingDatePattern(date[1]) ? LocalDate.parse(date[1], DateTimeFormatter.ofPattern("yy.MM.dd", Locale.KOREAN)) : null;

				String name = item.findElement(By.cssSelector(".popup-name")).getText();
				String href = item.findElement(By.cssSelector("a.popup-img-wrap")).getAttribute("href");
				String region = item.findElement(By.cssSelector(".popup-location")).getText().split(" ")[1];

				data.setId(String.valueOf(i));
				data.setName(name);
				data.setStartDate(startDate);
				data.setEndDate(endDate);
				data.setRegion(region);

				detailPageUrls.put(i, href);
				popUpStoreInfos.put(i, data);

			}
			getDetailInfos();
		}

		// 4. 상세 url 하나씩 접근
		private void getDetailInfos() {
			for (var detailPageUrl : detailPageUrls.entrySet()) {
				driver.get(detailPageUrl.getValue());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}

				setDetailInfos(detailPageUrl.getKey());
			}
		}

		// 5. 상세 데이터 저장
		private void setDetailInfos(int key) {
			String address = driver.findElement(By.cssSelector(".location")).getText();
			popUpStoreInfos.get(key).setAddress(address);

			var openHoursElements = driver.findElements(By.cssSelector(".popupdetail-time > ul > li"));
			List<String> openHours = new ArrayList<>();
			for (int i = 1; i < 8; i++) {
				openHours.add(openHoursElements.get(i).getText());
			}
			popUpStoreInfos.get(key).setOpenTimes(openHours);

			var liElements = driver.findElements(By.cssSelector(".popupdetail-icon-area > ul > li:not(.false)"));
			for (WebElement liElement : liElements) {
				switch (liElement.getText()) {
					case "주차가능" -> popUpStoreInfos.get(key).setParkingAvailability(true);
					case "주차불가" -> popUpStoreInfos.get(key).setParkingAvailability(false);
					case "입장료 무료" -> popUpStoreInfos.get(key).setTicketPrice(-1);
					case "입장료 유료" -> popUpStoreInfos.get(key).setTicketPrice(100000);
				}
			}
			// mongoDB에 데이터 저장
			infoRepo.insert(popUpStoreInfos.get(key));
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