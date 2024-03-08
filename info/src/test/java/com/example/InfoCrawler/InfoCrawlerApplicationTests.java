package com.example.InfoCrawler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.InfoCrawler.Domain.PopUpStoreInfo;
import com.example.InfoCrawler.repository.InfoRepository;

@SpringBootTest
class InfoCrawlerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	public InfoRepository infoRepo;

	@Test
	public void infoSave(){
		PopUpStoreInfo info = new PopUpStoreInfo();

		info.setName("수현");
		info.setTicketPrice(123456);
		info.setRegion("광진구");

		infoRepo.insert(info);
	}


}
