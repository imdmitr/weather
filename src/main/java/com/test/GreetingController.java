package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.test.owm.dto.CityWeather;
import com.test.service.WeatherRetriver;
import com.test.service.dto.WeatherDetails;

@Controller
public class GreetingController {

	@Autowired
	private WeatherRetriver retriver;
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	WeatherDetails currentWeather = retriver.getCurrentWeather(2643743);
        model.addAttribute("name", currentWeather.toString());
//        model.addAttribute("name", name);
        
        return "greeting";
    }

}
