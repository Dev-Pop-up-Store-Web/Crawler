package com.example.rediscache.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class PopUpStoreInfo {
	@Id
	private Long id;
	private String name, region, address, snsUrl, webUrl;
	private int ticketPrice;
	private LocalDate startDate, endDate;
	private LocalTime startTime, endTime;
	private boolean parkingAvailability;
}
