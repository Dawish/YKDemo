package com.eastelsoft.tv.widget.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class HomeViewPager extends ViewPager {

	public HomeViewPager(Context context) {
		super(context);
		init();
	}

	public HomeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		setChildrenDrawingOrderEnabled(false);
	}
	
	@Override
	protected boolean canScroll(View arg0, boolean arg1, int arg2, int arg3,
			int arg4) {
		return super.canScroll(arg0, arg1, arg2, arg3, arg4);
	}
	
	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		return super.getChildDrawingOrder(childCount, i);
	}
}
