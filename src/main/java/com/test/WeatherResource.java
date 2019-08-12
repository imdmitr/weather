package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.Favorite;
import com.test.dto.FavoriteItems;
import com.test.dto.WeatherDetails;
import com.test.service.FavoritesService;
import com.test.service.WeatherService;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class WeatherResource {

	@Autowired
	private WeatherService retriver;
	
	@Autowired
	private FavoritesService favoritesService;
	
    @GetMapping("/favorites")
    public FavoriteItems getFavoriteItems() {
        return favoritesService.getFavorites();
    }

    @PutMapping("/favorites/{id}/{city}")
    public void addFavoriteItems(@PathVariable int id, @PathVariable String city) {
    	Favorite item = new Favorite(id, city);  
		favoritesService.addItem(item);
    }

    @GetMapping("/{id}")
    public WeatherDetails getForCity(@PathVariable int id) {
    	return retriver.getCurrentWeather(id);
    }

    @GetMapping("/city/{city}")
    public WeatherDetails searchCity(@PathVariable String city) {
    	return retriver.getCurrentWeatherByCityName(city);
    }
    

	
}
