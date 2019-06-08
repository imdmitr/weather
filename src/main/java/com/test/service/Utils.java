package com.test.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class Utils {
	
	private Utils() {
	}

	public static int celcToF(int value) {
		return value * 9 / 5 + 32; 
	}
	
	public static int getTemperature(Float temp) {
		return Math.round(temp);
	}


	public static String getTimeOnly(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a"); 
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(date);
	}


	public static Date getEpocDate(long sec) {
		return new Date(sec * 1000);
	}

}
