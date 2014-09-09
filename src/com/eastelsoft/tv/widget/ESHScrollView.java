package com.eastelsoft.tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class ESHScrollView extends HorizontalScrollView {

	public ESHScrollView(Context context) {
		super(context);
	}

	public ESHScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ESHScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public int getHorizontalFadingEdgeLength() {
		return 200;
	}
	
	@Override
	public int getMaxScrollAmount() {
		return 500;
	}
	
	@Override
	public boolean isHorizontalScrollBarEnabled() {
		return false;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}
}
