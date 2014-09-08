package com.eastelsoft.tv;

import android.app.Application;

public class ESApplication extends Application {

	private static ESApplication instance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	
	public static ESApplication getInstance() {
		return instance;
	}
}
