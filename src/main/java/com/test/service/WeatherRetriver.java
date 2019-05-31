package com.test.service;

import com.test.service.dto.WeatherDetails;

public interface WeatherRetriver {
	public WeatherDetails getCurrentWeather(String id);

}
