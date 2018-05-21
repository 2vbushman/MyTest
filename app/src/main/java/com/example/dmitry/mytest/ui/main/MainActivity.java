package com.example.dmitry.mytest.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dmitry.mytest.R;

public final class MainActivity extends AppCompatActivity {
	private MainPresenter mPresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPresenter = new MainPresenter(this);
		findViewById(R.id.start_auth).setOnClickListener(v -> mPresenter.loadAuthFragment());
	}
}
