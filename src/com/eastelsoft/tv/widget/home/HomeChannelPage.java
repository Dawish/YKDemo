package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.view.View;

public class HomeChannelPage extends HomeBasePage {

	private String mTitle;
	
	public HomeChannelPage(Context context,String title) {
		super(context);
		mTitle = title;
	}
	
	@Override
	public String getPageTitle() {
		return mTitle;
	}

	@Override
	public void updateContentView() {

	}

	@Override
	protected View onCreateContentView() {
		View view = generatePageContent(null, R.layout.home_test, mOffsetX, mOffsetY, true);
		return view;
	}

}
