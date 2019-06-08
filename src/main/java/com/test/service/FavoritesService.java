package com.test.service;

import com.test.dto.Favorite;
import com.test.dto.FavoriteItems;

public interface FavoritesService {
	
	public FavoriteItems getFavorites();

	public void addItem(Favorite item);
}
