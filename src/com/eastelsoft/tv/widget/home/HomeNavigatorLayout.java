package com.eastelsoft.tv.widget.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class HomeNavigatorLayout extends LinearLayout {

	
	public HomeNavigatorLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(HORIZONTAL);
	}

	public HomeNavigatorLayout(Context context) {
		super(context);
		setOrientation(HORIZONTAL);
	}

	public HomeNavigatorLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOrientation(HORIZONTAL);
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		return super.getChildDrawingOrder(childCount, i);
	}
	
}
