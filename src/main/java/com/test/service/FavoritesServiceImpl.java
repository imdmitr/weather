package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.test.dto.Favorite;
import com.test.dto.FavoriteItems;

@Component
public class FavoritesServiceImpl implements FavoritesService {

	private FavoriteItems items;
	
	@Override
	public FavoriteItems getFavorites() {
		if (items == null) {
			items = loadFavoriteItems();
		}
		return items;
	}
	
	

	private FavoriteItems loadFavoriteItems() {
		FavoriteItems items = new FavoriteItems();
		items.getItems().add(new Favorite(2643743, "London"));
		items.getItems().add(new Favorite(1819729, "Hong Kong"));
		return items;
	}



	@Override
	public void addItem(Favorite item) {
		List<Favorite> list = getFavorites().getItems();
		Optional<Favorite> findFirst = list.stream().filter(f -> f.getId() == item.getId()).findFirst();
		if (!findFirst.isPresent()) {
			list.add(item);
		}
	}
}
