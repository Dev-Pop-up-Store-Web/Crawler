package com.example.InfoCrawler.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "infos")
public class PopUpStoreInfo{

	@Id
	private String id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<String> openTimes;
	private String region;
	private String address;
	private int ticketPrice;
	private String snsUrl;
	private String webUrl;
	private boolean parkingAvailability;
	private List<String> urls;

	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime modifiedDate;

		public PopUpStoreInfo(){
			this.id = null;
			this.name = null;
			this.startDate = null;
			this.endDate = null;
			this.openTimes = null;
			this.region = null;
			this.address = null;
			this.ticketPrice = -1;
			this.snsUrl = null;
			this.webUrl = null;
			this.parkingAvailability = false;
			this.createdDate = LocalDateTime.now();
			this.urls = null;

		}

		public PopUpStoreInfo(String id, String name, LocalDate startDate, LocalDate endDate, List<String> openTimes, String region, String address, int ticketPrice, String snsUrl,
		String webUrl, boolean parkingAvailability, List<String> urls) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.openTimes = openTimes;
		this.region = region;
		this.address = address;
		this.ticketPrice = ticketPrice;
		this.snsUrl = snsUrl;
		this.webUrl = webUrl;
		this.parkingAvailability = parkingAvailability;
		this.createdDate = LocalDateTime.now();
		this.urls = urls;
	}
}
