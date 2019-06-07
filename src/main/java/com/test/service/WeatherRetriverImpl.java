package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.owm.dto.CityWeather;
import com.test.owm.dto.Weather;
import com.test.service.dto.WeatherDetails;

@Component
@ConfigurationProperties(prefix="owm")
public class WeatherRetriverImpl implements WeatherRetriver {

//	@Value("${appKey}")
	private String appKey;

	private String url;
	
	private String uriGet;
	
	private String units;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public WeatherDetails getCurrentWeather(int id) {
		CityWeather cityWeather = restTemplate.getForObject(url + uriGet, CityWeather.class, appKey, units, id);
		return convertToDetails(cityWeather);
	}


	private WeatherDetails convertToDetails(CityWeather cityWeather) {
		WeatherDetails rez = new WeatherDetails();
		rez.setCity(cityWeather.getName());
		rez.setId(cityWeather.getId());
		if (!cityWeather.getWeather().isEmpty()) {
			Weather weather = cityWeather.getWeather().get(0);
			rez.setDescription(weather.getDescription());
		}
		rez.setSunrise(getTimeOnly(getEpocDate(cityWeather.getSys().getSunrise())));
		rez.setSunset(getTimeOnly(getEpocDate(cityWeather.getSys().getSunset())));
		
		int tempC = getTemperature(cityWeather.getMain().getTemp());
		rez.setTempC(tempC);
		rez.setTempF(celcToF(tempC));
		rez.setToday(getEpocDate(cityWeather.getDt()));
		return rez;
	}
	
	private int celcToF(int value) {
		return value * 9 / 5 + 32; 
	}
	
	private int getTemperature(Float temp) {
		return Math.round(temp);
	}


	private String getTimeOnly(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a"); 
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(date);
	}


	private Date getEpocDate(long sec) {
		return new Date(sec * 1000);
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUriGet() {
		return uriGet;
	}


	public void setUriGet(String uriGet) {
		this.uriGet = uriGet;
	}


	public String getUnits() {
		return units;
	}


	public void setUnits(String units) {
		this.units = units;
	}


	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
}
