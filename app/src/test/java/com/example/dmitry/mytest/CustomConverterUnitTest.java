package com.example.dmitry.mytest;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.dmitry.mytest.api.CustomGsonResponseBodyConverter.REGEX;
import static org.junit.Assert.assertEquals;

public class CustomConverterUnitTest {
	@Test
	public void assert_regex_correct() throws Exception {
		final String response = "{\"query\":{\"count\":1,\"created\":\"2018-05-20T15:09:46Z\",\"lang\":\"ru-RU\",\"results\":" +
				"{\"channel\":{\"item\":{\"condition\":{\"code\":\"26\",\"date\":\"Sun, 20 May 2018 05:00 PM MSK\",\"temp\":\"12\",\"text\":\"Cloudy\"}}}}}}";

		final String customResponse = "{\"code\":\"26\",\"date\":\"Sun, 20 May 2018 05:00 PM MSK\",\"temp\":\"12\",\"text\":\"Cloudy\"}";

		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(response);
		if (matcher.matches()) {
			assertEquals(customResponse, matcher.group(1));
		} else {
			throw new Exception("Error");
		}
	}
}
