package com.example.rediscache;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rediscache.domain.PopUpStoreInfo;
import com.example.rediscache.repository.PopUpStoreInfoRepository;

@SpringBootTest
public class PopUpStoreInfoRepositoryTest {

	@Autowired
	private PopUpStoreInfoRepository repo;

	@Test
	void saveTest() {
		PopUpStoreInfo store = new PopUpStoreInfo(1L, "경희대학교", "용인", "경기도 용인시 어쩌구 저쩌구", "경희대인스타", "경희대닷컴", 0,
			LocalDate.now(), null, LocalTime.now(), null, true);

		repo.save(store);
		repo.findById(store.getId());
		repo.count();
		repo.delete(store);
	}

	@Test
	void getTest() {
		System.out.println("--------------\nStart *getTest*\n--------------");
		repo.findById(1L).ifPresent(System.out::println);
		System.out.println("--------------");
		// repo.findAll().forEach(System.out::println);
	}
}
