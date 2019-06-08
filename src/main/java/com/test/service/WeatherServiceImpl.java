package com.test.service;

import static com.test.service.Utils.celcToF;
import static com.test.service.Utils.getEpocDate;
import static com.test.service.Utils.getTemperature;
import static com.test.service.Utils.getTimeOnly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.dto.WeatherDetails;
import com.test.owm.dto.CityWeather;
import com.test.owm.dto.Weather;

@Component
@ConfigurationProperties(prefix="owm")
public class WeatherServiceImpl implements WeatherService {

//	@Value("${appKey}")
	private String appKey;

	private String url;
	
	private String uriGet;
	
	private String uriByCity;
	
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

	public String getUriByCity() {
		return uriByCity;
	}


	public void setUriByCity(String uriByCity) {
		this.uriByCity = uriByCity;
	}


	@Override
	public WeatherDetails getCurrentWeatherByCityName(String city) {
		CityWeather cityWeather = restTemplate.getForObject(url + uriByCity, CityWeather.class, appKey, units, city);
		return convertToDetails(cityWeather);
	}
	
}
