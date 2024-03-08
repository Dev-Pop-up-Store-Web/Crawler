package com.example.InfoCrawler;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.InfoCrawler.Domain.PopUpStoreInfo;
import com.example.InfoCrawler.repository.InfoRepository;

@SpringBootTest
public class InfoRepositoryTest {

	@Autowired
	private InfoRepository infoRepo;

	@Test
	void saveTest() {
		PopUpStoreInfo store = new PopUpStoreInfo("id", "경희대학교", LocalDate.now(), LocalDate.now(), null, "광진구",
			"서울특별시 광진구", -1, null, null, true);

		infoRepo.save(store);
	}

	@Test
	public void infoSave(){
		// PopUpStoreInfo info = new PopUpStoreInfo();
		//
		// info.setId("");
		// info.setName("수현");
		// info.setTicketPrice(123456);
		// info.setRegion("광진구");
		//
		// infoRepo.save(info);
		infoRepo.deleteAll();

	}

	@Test
	void getTest() {
		System.out.println("getTest");
		infoRepo.findAll().forEach(System.out::println);
	}


}
