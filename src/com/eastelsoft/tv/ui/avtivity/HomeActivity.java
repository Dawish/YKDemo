package com.eastelsoft.tv.ui.avtivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;
import com.eastelsoft.tv.widget.home.HomeBasePage;
import com.eastelsoft.tv.widget.home.HomeRecommendPage;

public class HomeActivity extends BaseActivity {

	private ArrayList<HomeBasePage> mPagerSpecs;
	private ViewGroup mTopNavigatorContainer;
	private ViewPager mPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
	}
	
	private void initUI() {
		setContentView(R.layout.activity_main);
		//initToolBar();
		initViewPager();
	}
	
	private void initViewPager() {
		mPager = (ViewPager)findViewById(R.id.viewpager);
		mPager.setOffscreenPageLimit(1);
		mPagerSpecs = new ArrayList<>();
		mPagerSpecs.add(new HomeRecommendPage(this));
		initViewPagerNavigation();
	}
	
	private void initViewPagerNavigation() {
		mTopNavigatorContainer = (ViewGroup)findViewById(R.id.top_viewpager_navigator);
		ViewGroup mParent = (ViewGroup)mTopNavigatorContainer.getParent();
		mParent.removeView(mTopNavigatorContainer);
		for (int i = 0; i < mPagerSpecs.size(); i++) {
			HomeBasePage page = mPagerSpecs.get(i);
			View.inflate(this, R.layout.home_top_navigator_item, mTopNavigatorContainer);
			((TextView)mTopNavigatorContainer.getChildAt(i)).setText(page.getPageTitle());
		}
		
	}
}
