package com.eastelsoft.tv.widget.home;

import android.view.View;

public abstract interface IViewPagerContent {

	public abstract View createContentView();
	public abstract String getPageTitle();
	public abstract void updateContentView();
}
