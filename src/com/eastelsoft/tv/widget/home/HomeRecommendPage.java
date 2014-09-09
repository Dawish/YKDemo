package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.view.View;

public class HomeRecommendPage extends HomeBasePage {
	
	private String mTitle;

	public HomeRecommendPage(Context context,String title) {
		super(context);
		mTitle = title;
	}

	@Override
	public String getPageTitle() {
		return mTitle;
	}

	@Override
	protected View onCreateContentView() {
		View view = generatePageContent(null, R.layout.home_page_recommend_template, mOffsetX, mOffsetY, true);
		return view;
	}

	@Override
	public void updateContentView() {
		
	}
}
