package com.eastelsoft.tv.widget.home;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class HomePagerAdapter extends PagerAdapter {
	
	private Context mContext;
	private List<HomeBasePage> mPages;
	
	public HomePagerAdapter(Context context, List<HomeBasePage> pages) {
		mContext = context;
		mPages = pages;
	}

	@Override
	public int getCount() {
		return mPages.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		HomeBasePage page = mPages.get(position);
		View view = page.createContentView();
		container.addView(view);
		return view;
	}

}
