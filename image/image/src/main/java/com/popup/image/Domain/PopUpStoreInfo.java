package com.popup.image.Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "infos")
@Getter
@Setter
@NoArgsConstructor
public class PopUpStoreInfo {

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

	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	private List<String> urls;

}
