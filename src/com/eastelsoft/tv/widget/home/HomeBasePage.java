package com.eastelsoft.tv.widget.home;

import android.content.Context;
import android.view.View;

public class HomeBasePage implements IViewPagerContent{

	private Context mContext;
	
	public HomeBasePage(Context context) {
		mContext = context;
	}

	@Override
	public View createContentView() {
		return null;
	}

	@Override
	public String getPageTitle() {
		return "BASE";
	}

	@Override
	public void updateContentView() {
	}

}