package com.eastelsoft.tv.widget;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class ESLoading extends ESLoadingView {

	private int loadingSize;
	
	public ESLoading(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ESLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ESLoading(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		loadingSize = dip2px(48.0F);
		setBackgroundResource(R.drawable.play_loading_big);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(loadingSize, loadingSize);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public int dip2px(float paramFloat){
		return (int) (0.5F + paramFloat * getContext().getResources().getDisplayMetrics().density);
	}
}
