package com.example.InfoCrawler.service;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

	private WebDriverManager() {
		// private 생성자로 외부에서 인스턴스 생성을 막음
	}

	public static WebDriver initChromeDriver() {
		return setChromeDriver();
	}

	private static WebDriver setChromeDriver() {
		try{
			String osName = System.getProperty("os.name").toLowerCase();
			ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder();

			// mac
			if (osName.contains("mac"))
				serviceBuilder.usingDriverExecutable(new File("/usr/local/bin/chromedriver"));

				// raspberryPi
			else if (osName.contains("linux") && osName.contains("arm"))
				serviceBuilder.usingDriverExecutable(new File("/usr/local/bin/chromedriver"));

				// windows
			// else if (osName.contains("win"))
			// 	serviceBuilder.usingDriverExecutable(new File("C:\path\to\chromedriver.exe"));

				// docker container
			else if (osName.contains("linux"))
				serviceBuilder.usingDriverExecutable(new File("/usr/local/bin/chromedriver"));

			ChromeDriverService service = serviceBuilder.usingPort(9516).build(); // -> 포트 다르게 설정
			service.start();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless"); // headless 모드 활성화
			options.addArguments("--no-sandbox"); // no-sandbox 옵션 추가
			// options.addArguments("--disable-dev-shm-usage"); //  unknown error: session deleted because of page crash
			// options.addArguments("--remote-debugging-pipe");

			return new ChromeDriver(service, options);
		}
		catch(Exception ex){
			return null;
		}
	}
}