package com.example.dmitry.mytest.api;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public final class CustomGsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {
	private final Gson GSON;
	private final TypeAdapter<T> ADAPTER;
	public static final String REGEX = ".*(\\{\"code\"[^}]*\\})\\}*";
	private final Pattern PATTERN;

	CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		GSON = gson;
		ADAPTER = adapter;
		PATTERN = Pattern.compile(REGEX);
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		String response = value.string();
		System.out.println(response);
		Matcher matcher = PATTERN.matcher(response);

		JsonReader jsonReader;
		if(matcher.matches()){
			jsonReader = GSON.newJsonReader(new StringReader(matcher.group(1)));
		} else {
			throw new IOException("Weather data corrupted");
		}

		try {
			return ADAPTER.read(jsonReader);
		} finally {
			value.close();
		}
	}
}