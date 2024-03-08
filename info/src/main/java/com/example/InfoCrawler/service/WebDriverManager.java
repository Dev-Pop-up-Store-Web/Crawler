package com.example.InfoCrawler.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

	private static WebDriver driver;
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";

	private WebDriverManager() {
		// private 생성자로 외부에서 인스턴스 생성을 막음
	}

	public static WebDriver initChromeDriver() {
		setDriverOptions();
		driver = new ChromeDriver(getChromeOptions());
		return driver;
	}

	private static void setDriverOptions() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		return options;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
