package hello;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.test.owm.dto.CityWeather;

public class ApplicationTestTest {

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		// http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=6e6a876862d407ef786868c2da49ccd2
//		http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=6e6a876862d407ef786868c2da49ccd2&units=metric
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

		
		forObject = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/weather?&APPID={appkey}&id={city}", CityWeather.class,
				"6e6a876862d407ef786868c2da49ccd2", "2643743");
		System.out.println(forObject);
		
		
		fail("Not yet implemented");
	}

}
