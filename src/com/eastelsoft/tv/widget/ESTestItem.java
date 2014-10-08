package com.eastelsoft.tv.widget;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class ESTestItem extends RelativeLayout {
	
	private ImageView mFocus;
	private ImageView mShadow;
	
	private Animation scaleSmallAnimation;
	private Animation scaleBigAnimation;

	public ESTestItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ESTestItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ESTestItem(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		setChildrenDrawingOrderEnabled(true);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus) {
			zoomOut();
		} else {
			zoomIn();
		}
	}

	private void zoomIn() {
		if (scaleSmallAnimation == null) {
			scaleSmallAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_small);
		}
		startAnimation(scaleSmallAnimation);
	}
	
	private void zoomOut() {
		if (scaleBigAnimation == null) {
			scaleBigAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_big);
		}
		startAnimation(scaleBigAnimation);
	}

}
