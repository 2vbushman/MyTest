package com.example.dmitry.mytest.ui.auth;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitry.mytest.R;
import com.example.dmitry.mytest.data.Condition;

public final class AuthorizationActivity
		extends AppCompatActivity
		implements AuthorizationView {
	private AuthorizationPresenter mPresenter;
	private EditText mEmail;
	private EditText mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		mPresenter = new AuthorizationPresenter(this);

		prepareActionBar();
		mEmail = findViewById(R.id.e_mail);
		mPassword = findViewById(R.id.pass);

		findViewById(R.id.password_hint).setOnClickListener(this::onClick);
		Button enter = findViewById(R.id.auth);
		enter.setEnabled(false);
		enter.setOnClickListener(this::onClick);
		mPresenter.canEnter(mEmail, mPassword, enter);
	}

	private void prepareActionBar() {
		setSupportActionBar(findViewById(R.id.toolbar));
		ActionBar bar = getSupportActionBar();
		if (bar != null) {
			bar.setDisplayHomeAsUpEnabled(true);
			bar.setDisplayShowHomeEnabled(true);
			bar.setDisplayShowTitleEnabled(false);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onClick(View v) {
		final int id = v.getId();
		switch (id) {
			case R.id.password_hint:
				mPresenter.showPasswordHintDialog();
				break;
			case R.id.auth:
				final String login = mEmail.getText().toString().trim();
				final String pass = mPassword.getText().toString().trim();
				mPresenter.tryAuthorize(login, pass);
				break;
		}
	}

	@Override
	public void onSnackbarShow(Condition condition) {
		if (condition != null && !condition.getText().equals(Condition.errorMessage)) {
			Snackbar.make(mEmail, getFormattedString(condition), Snackbar.LENGTH_LONG).show();
		} else {
			Snackbar.make(mEmail, R.string.error_mess, Snackbar.LENGTH_LONG).show();
		}
	}

	private String getFormattedString(Condition condition) {
		Resources res = getResources();
		try {
			if (condition != null) {
				return res.getString(
						R.string.snack_message,
						condition.getText(),
						Integer.parseInt(condition.getTemp()));
			}
		} catch (NumberFormatException ignored) {
		}
		return res.getString(R.string.error_mess);
	}
}
