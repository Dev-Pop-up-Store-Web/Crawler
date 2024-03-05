package com.example.rediscache.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "store", timeToLive = 300)
public class PopUpStoreInfo {
	@Id
	private String id;
	private String name;
	private String region;
	private String address;
	private String snsUrl;
	private String webUrl;
	private int ticketPrice;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean parkingAvailability;
}
