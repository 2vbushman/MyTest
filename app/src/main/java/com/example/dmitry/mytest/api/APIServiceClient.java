package com.example.dmitry.mytest.api;

import com.example.dmitry.mytest.data.Condition;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIServiceClient {
	@GET("yql")
	Call<Condition> getWeatherForecast(
			@Query("q") String q,
			@Query("format")String format);
}
