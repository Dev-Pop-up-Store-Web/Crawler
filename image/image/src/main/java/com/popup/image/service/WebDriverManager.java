package com.popup.image.service;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class WebDriverManager {
	private static WebDriver driver;

	public static WebDriver initChromeDriver() {
		return setChromeDriver();
	}

	// private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	// // chromedriver 설치 경로 입력
	// private static final String WEB_DRIVER_PATH = "C:\\Users\\seol\\Downloads\\chromedriver\\chromedriver.exe";

	private WebDriverManager() {
		// private 생성자로 외부에서 인스턴스 생성을 막음
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
			else if (osName.contains("win"))
				// serviceBuilder.usingDriverExecutable(new File("C:\\path\\to\\chromedriver.exe"));
				serviceBuilder.usingDriverExecutable(new File("C:\\Users\\seol\\Downloads\\chromedriver\\chromedriver.exe"));

				// docker container
			else if (osName.contains("linux"))
				serviceBuilder.usingDriverExecutable(new File("/usr/local/bin/chromedriver"));

			ChromeDriverService service = serviceBuilder.usingPort(9387).build(); // -> 포트 다르게 설정
			service.start();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless"); // headless 모드 활성화
			options.addArguments("--no-sandbox"); // no-sandbox 옵션 추가
			options.addArguments("--disable-dev-shm-usage"); //  unknown error: session deleted because of page crash
			// options.addArguments("--remote-debugging-pipe");

			return new ChromeDriver(service, options);
		}
		catch(Exception ex){
			return null;
		}
	}
}
