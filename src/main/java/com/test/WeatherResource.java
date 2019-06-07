package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.Favorite;
import com.test.dto.FavoriteItems;
import com.test.service.WeatherRetriver;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherResource {

	@Autowired
	private WeatherRetriver retriver;
	
    @GetMapping("/favorites")
    public FavoriteItems getFavoriteItems() {
        return loadFavoriteItems();
    }

	private FavoriteItems loadFavoriteItems() {
		FavoriteItems items = new FavoriteItems();
		items.getItems().add(new Favorite(2643743, "London"));
//		fi.getItems().add(new Favorite(2643743, "Hong Kong"));
		return items;
	}
	
}
