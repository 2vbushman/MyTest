package com.example.dmitry.mytest.ui.auth;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitry.mytest.R;
import com.example.dmitry.mytest.api.WeatherLoader;
import com.example.dmitry.mytest.data.Condition;
import com.example.dmitry.mytest.util.CredentialsValidator;
import com.example.dmitry.mytest.util.NetworkUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

final class AuthorizationPresenter {
	private final Context CONTEXT;
	private AuthorizationView mView;

	AuthorizationPresenter(Context context) {
		CONTEXT = context;
		if (context instanceof AuthorizationView) {
			mView = (AuthorizationView) context;
		}
	}

	public void showPasswordHintDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(CONTEXT, R.style.AppCompatAlertDialogStyle);
		builder.setTitle(R.string.pass_hint_title);
		builder.setMessage(R.string.pass_hint_message);
		builder.setNegativeButton(R.string.dialog_close, null);
		builder.show();
	}

	public void canEnter(EditText email, EditText pass, Button enter) {
		TextWatcher watcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				final String emailText = email.getText().toString().trim();
				final String passText = pass.getText().toString().trim();

				enter.setEnabled(CredentialsValidator.validateEmail(emailText)
						&& CredentialsValidator.validatePassword(passText));
			}
		};

		email.addTextChangedListener(watcher);
		pass.addTextChangedListener(watcher);
	}

	public void tryAuthorize(@SuppressWarnings("unused") String login,
	                         @SuppressWarnings("unused") String password) {
		if(NetworkUtil.isNetworkConnected(CONTEXT)) {
			WeatherLoader.loadWeatherData(createServerResponseObserver());
		} else {
			mView.onSnackbarShow(WeatherLoader.getErrorConditionObject());
		}
	}

	private Observer<Condition> createServerResponseObserver() {
		return new Observer<Condition>() {
			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onNext(Condition condition) {
				mView.onSnackbarShow(condition);
			}

			@Override
			public void onError(Throwable e) {
				mView.onSnackbarShow(WeatherLoader.getErrorConditionObject());
			}

			@Override
			public void onComplete() {
			}
		};
	}
}
