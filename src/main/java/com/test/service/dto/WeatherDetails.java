package com.test.service.dto;

import java.util.Date;

public class WeatherDetails {
	private Date today;
	private String city;
	private int id;
	private String description;
	private int tempC;
	private int tempF;
	private String sunset;
	private String sunrise;

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTempC() {
		return tempC;
	}

	public void setTempC(int tempC) {
		this.tempC = tempC;
	}

	public int getTempF() {
		return tempF;
	}

	public void setTempF(int tempF) {
		this.tempF = tempF;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	@Override
	public String toString() {
		return "WeatherDetails [today=" + today + ", city=" + city + ", id=" + id + ", description=" + description
				+ ", tempC=" + tempC + ", tempF=" + tempF + ", sunset=" + sunset + ", sunrise=" + sunrise + "]";
	}
	

}
