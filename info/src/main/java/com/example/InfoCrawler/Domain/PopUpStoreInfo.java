package com.example.InfoCrawler.Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "infos")
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

	private LocalTime createdDate;
	private LocalTime updatedDate;

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
		}

	   public PopUpStoreInfo(String id, String name, LocalDate startDate, LocalDate endDate, List<String> openTimes, String region, String address, int ticketPrice, String snsUrl,
		String webUrl, boolean parkingAvailability) {
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
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public List<String> getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(List<String> openTimes) {
		this.openTimes = openTimes;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getSnsUrl() {
		return snsUrl;
	}

	public void setSnsUrl(String snsUrl) {
		this.snsUrl = snsUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public boolean isParkingAvailable() {
		return parkingAvailability;
	}

	public void setParkingAvailability(boolean parkingAvailability) {
		this.parkingAvailability = parkingAvailability;
	}

	public LocalTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	// @Override
	// public String toString() {
	// 	return "PopUpStoreInfo{" +
	// 		"\n id='" + id + '\'' +
	// 		", \n name='" + name + '\'' +
	// 		", \n startDate='" + startDate + '\'' +
	// 		", \n endDate='" + endDate + '\'' +
	// 		", \n region='" + region + '\'' +
	// 		", \n address='" + address + '\'' +
	// 		", \n ticketPrice='" + ticketPrice + '\'' +
	// 		", \n snsUrl='" + snsUrl + '\'' +
	// 		", \n webUrl='" + webUrl + '\'' +
	// 		", \n parkingAvailability='" + parkingAvailability + '\'' +
	// 		'}';
	// }
}
