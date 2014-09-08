package com.eastelsoft.tv.ui.avtivity;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_welcome);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		hander.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2 * 1000);
	}
	
	private Handler hander = new Handler();
}
