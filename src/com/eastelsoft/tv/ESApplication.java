package com.eastelsoft.tv;

import com.eastelsoft.tv.util.CrashHandler;

import android.app.Application;

public class ESApplication extends Application {

	private static ESApplication instance = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		CrashHandler crashHandler = CrashHandler.getInstance();  
        crashHandler.init(getApplicationContext());  
	}
	
	public static ESApplication getInstance() {
		return instance;
	}
}
