package com.eastelsoft.tv.ui.avtivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;
import com.eastelsoft.tv.widget.ESItemView;
import com.eastelsoft.tv.widget.home.HomeBasePage;
import com.eastelsoft.tv.widget.home.HomeChannelPage;
import com.eastelsoft.tv.widget.home.HomePagerAdapter;
import com.eastelsoft.tv.widget.home.HomeRecommendPage;

public class HomeActivity extends BaseActivity implements OnClickListener{

	private HomePagerAdapter mPagerAdapter;
	private ArrayList<HomeBasePage> mPagerSpecs;
	private ViewGroup mTopNavigatorContainer;
	private ViewPager mPager;
	
	private ImageView mSearch;
	private ImageView mHistory;
	private ImageView mSetting;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
	}
	
	private void initUI() {
		setContentView(R.layout.activity_main);
		findViews();
		//initToolBar();
		initViewPager();
	}
	
	private void findViews() {
		mSearch = (ImageView)findViewById(R.id.search);
		mSearch.setOnClickListener(this);
		mHistory = (ImageView)findViewById(R.id.history);
		mHistory.setOnClickListener(this);
		mSetting = (ImageView)findViewById(R.id.setting);
		mSetting.setOnClickListener(this);
	}
	
	private void initViewPager() {
		mPager = (ViewPager)findViewById(R.id.viewpager);
		mPager.setOffscreenPageLimit(1);
		mPagerSpecs = new ArrayList<>();
		mPagerSpecs.add(new HomeRecommendPage(this,"首页"));
		mPagerSpecs.add(new HomeChannelPage(this,"频道"));
		mPagerSpecs.add(new HomeChannelPage(this,"排行榜"));
		mPagerSpecs.add(new HomeChannelPage(this,"我的"));
		initViewPagerNavigation();
		
		mPagerAdapter = new HomePagerAdapter(this, mPagerSpecs);
		mPager.setAdapter(mPagerAdapter);
	}
	
	private void initViewPagerNavigation() {
		mTopNavigatorContainer = (ViewGroup)findViewById(R.id.top_viewpager_navigator);
		ViewGroup.LayoutParams layoutParams = mTopNavigatorContainer.getLayoutParams();
		ViewGroup mParent = (ViewGroup)mTopNavigatorContainer.getParent();
		mParent.removeView(mTopNavigatorContainer);
		for (int i = 0; i < mPagerSpecs.size(); i++) {
			HomeBasePage page = mPagerSpecs.get(i);
			View.inflate(this, R.layout.home_top_navigator_item, mTopNavigatorContainer);
			TextView child = (TextView)mTopNavigatorContainer.getChildAt(-1+mTopNavigatorContainer.getChildCount());
			child.setText(page.getPageTitle());
			child.setFocusable(true);
			child.setFocusableInTouchMode(true);
			child.setClickable(true);
			if (i == 0) {
				child.requestFocus();
			}
		}
		mParent.addView(mTopNavigatorContainer, layoutParams);
		mTopNavigatorContainer.bringToFront();
		mTopNavigatorContainer.setClipChildren(false);
		mTopNavigatorContainer.setClipToPadding(false);
		
		mTopNavigatorContainer.setNextFocusUpId(R.id.top_tool_bar);
		mTopNavigatorContainer.setNextFocusDownId(R.id.viewpager);
		
		int count = mTopNavigatorContainer.getChildCount();
		for (int i = 0; i < count; i++) {
			View tv = (View)mTopNavigatorContainer.getChildAt(i);
			tv.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						int index = mTopNavigatorContainer.indexOfChild(v);
						mPager.setCurrentItem(index);
					}
				}
			});
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search:
			Toast.makeText(HomeActivity.this, "search click", Toast.LENGTH_SHORT).show();
			break;
		case R.id.history:
			Toast.makeText(HomeActivity.this, "history click", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting:
			Toast.makeText(HomeActivity.this, "setting click", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
