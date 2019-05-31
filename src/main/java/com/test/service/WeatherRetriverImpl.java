package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.test.ConfigProperties;
import com.test.owm.dto.CityWeather;
import com.test.service.dto.WeatherDetails;

public class WeatherRetriverImpl implements WeatherRetriver {

	@Autowired
	private ConfigProperties configProp;
	
	@Value("${owm.appKey}")
	private String appKey;

	@Value("${owm.weatherUri}")
	private String url;
	
	
	@Override
	public WeatherDetails getCurrentWeather(String id) {
		// TODO Auto-generated method stub
		
		String city = "London";
		CityWeather forObject = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/weather?&APPID={appkey}&q={city}", CityWeather.class,
				"6e6a876862d407ef786868c2da49ccd2", city);
		System.out.println(forObject);

		String date = "1559333181";
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a"); 
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date expiry = new Date(1559333181L * 1000);
		System.out.println(df.format(expiry));
		return null;
	}

}
