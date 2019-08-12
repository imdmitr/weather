/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.test.WeatherResource;
import com.test.dto.Favorite;
import com.test.dto.FavoriteItems;
import com.test.service.FavoritesService;
import com.test.service.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WeatherResource.class})
@AutoConfigureMockMvc
@EnableWebMvc
@ContextConfiguration
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
	private WeatherService retriver;
	
    @MockBean
	private FavoritesService favoritesService;    

    @Test
    public void favorites() throws Exception {
    	
    	FavoriteItems items = new FavoriteItems();
    	Favorite fav = new Favorite(1, "testCity");
		items.getItems().add(fav);
		when(favoritesService.getFavorites()).thenReturn(items);
        mockMvc.perform(get("/weather/favorites")
        		.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
        		)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", Matchers.hasSize(1)))
        		.andExpect(jsonPath("$.items[0].id", Matchers.is(1)))
        		.andExpect(jsonPath("$.items[0].city", Matchers.is("testCity")));
    }


}
