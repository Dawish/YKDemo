package com.eastelsoft.tv.widget;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ESLoadingView extends View {

	public ESLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ESLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ESLoadingView(Context context) {
		super(context);
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if (visibility == View.INVISIBLE || visibility == View.GONE) {
			stopAnimation();
		} else {
			startAnimation();
		}
	}
	
	protected void startAnimation() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading);
		animation.setRepeatCount(-1);
		startAnimation(animation);
	}
	
	protected void stopAnimation() {
		clearAnimation();
	}
}
