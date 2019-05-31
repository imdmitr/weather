package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.test.owm.dto.CityWeather;
import com.test.service.WeatherRetriver;

@Controller
public class GreetingController {

	@Autowired
	private WeatherRetriver retriver;
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        
        retriver.getCurrentWeather(123);
        RestTemplate restTemplate = new RestTemplate();
        //http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=6e6a876862d407ef786868c2da49ccd2
        
        String city = "London";
		CityWeather forObject = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?&APPID={appkey}&q={city}", CityWeather.class, "6e6a876862d407ef786868c2da49ccd2", city);
        return "greeting";
    }

}
