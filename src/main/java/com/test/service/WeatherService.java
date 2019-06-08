package com.test.service;

import com.test.dto.WeatherDetails;

public interface WeatherService {
	public WeatherDetails getCurrentWeather(int id);
	
	public WeatherDetails getCurrentWeatherByCityName(String city);

}
