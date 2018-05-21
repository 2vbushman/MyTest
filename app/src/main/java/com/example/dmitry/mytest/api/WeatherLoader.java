package com.example.dmitry.mytest.api;

import android.support.annotation.NonNull;

import com.example.dmitry.mytest.data.Condition;
import com.example.dmitry.mytest.util.DebugUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class WeatherLoader {
	private static final String BASE = "http://query.yahooapis.com/v1/public/";
	private static final String CITY = "Moscow";
	private static final String DEGREE = "c";
	private static final String YQL_QUERY = "select item.condition from weather.forecast " +
			"where woeid in " +
			"(select woeid from geo.places(1) " +
			"where text=\"" + CITY + "\") and u=\"" + DEGREE + "\"";

	public static void loadWeatherData(Observer<Condition> observer) {
		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(BASE)
				.addConverterFactory(LenientGsonConverterFactory.create(gson));

		Retrofit retrofit = builder.build();
		prepareAPIServiceClient(retrofit, observer);
	}

	private static void prepareAPIServiceClient(Retrofit retrofit, Observer<Condition> observer){
		if(retrofit == null) return;

		APIServiceClient apiServiceClient = retrofit.create(APIServiceClient.class);
		Call<Condition> call = apiServiceClient.getWeatherForecast(YQL_QUERY, "json");
		call.enqueue(new Callback<Condition>() {
			@Override
			public void onResponse(@NonNull Call<Condition> call, @NonNull Response<Condition> response) {
				if (response.isSuccessful()) {
					Condition condition = response.body();
					onSuccess(observer, condition);
				} else {
					onError(observer);
				}
			}

			@Override
			public void onFailure(@NonNull Call<Condition> call, @NonNull Throwable t) {
				DebugUtil.print("Query error " + t);
				onError(observer);
			}
		});
	}

	public static Condition getErrorConditionObject() {
		return new Condition();
	}

	private static void onSuccess(Observer<Condition> observer, Condition condition){
		if (condition == null) condition = getErrorConditionObject();
		if (observer != null) {
			Observable<Condition> observable = Observable.fromArray(condition);
			observable.subscribe(observer);
		}
	}

	private static void onError(Observer<Condition> observer){
		Condition condition = getErrorConditionObject();
		if (observer != null) {
			Observable<Condition> observable = Observable.fromArray(condition);
			observable.subscribe(observer);
		}
	}
}
