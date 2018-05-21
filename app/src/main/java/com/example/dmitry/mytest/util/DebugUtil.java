package com.example.dmitry.mytest.util;

import android.util.Log;

import com.example.dmitry.mytest.BuildConfig;

public class DebugUtil {
	public static void print(final Object object) {
		int maxLogSize = 2048;
		if (BuildConfig.DEBUG) {
			final String logTag = "Test";
			if (object == null) {
				Log.d(logTag, "--- attention object is null --- !!!!!!!!!!!!!");
				return;
			}
			if (!object.getClass().equals(String.class)) {
				Log.d(logTag, object.toString());
			} else {
				if (((String) object).isEmpty()) {
					Log.d(logTag, "--- string empty --- !!!!!!!!!!!!!");
					return;
				} else if (object.equals(" ")) {
					Log.d(logTag, "--- string equals space --- !!!!!!!!!!!!!");
					return;
				}
				String str = (String) object;
				for (int i = 0; i <= str.length() / maxLogSize; i++) {
					int start = i * maxLogSize;
					int end = (i + 1) * maxLogSize;
					end = end > str.length() ? str.length() : end;
					Log.d(logTag, str.substring(start, end));
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public static void foreach(Iterable<Object>collection) {
		if(collection == null) {
			print("--- attention collection is null --- !!!!!!!!!!!!!");
			print("++++++++++++++++++++++++++++++++++++");
			return;
		}

		for (Object object : collection) {
			print(object);
		}
		print("++++++++++++++++++++++++++++++++++++");
	}
}
