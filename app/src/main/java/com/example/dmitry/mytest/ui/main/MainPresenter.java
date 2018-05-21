package com.example.dmitry.mytest.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.dmitry.mytest.ui.auth.AuthorizationActivity;

final class MainPresenter {
	private final AppCompatActivity ACTIVITY;

	MainPresenter(AppCompatActivity activity) {
		ACTIVITY = activity;
	}

	public void loadAuthFragment(){
		Intent myIntent = new Intent (ACTIVITY, AuthorizationActivity.class);
		ACTIVITY.startActivity(myIntent);
	}
}
