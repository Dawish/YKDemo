package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class HomeItemContainer extends RelativeLayout {
	
	private Rect mBound;
	private Drawable mDrawable;
	private Rect mRect;
	
	private Animation scaleSmallAnimation;
	private Animation scaleBigAnimation;
	
	public HomeItemContainer(Context context) {
		super(context);
		init();
	}

	public HomeItemContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public HomeItemContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	protected void init() {
		setWillNotDraw(false);
		mRect = new Rect();
		mBound = new Rect();
		mDrawable = getResources().getDrawable(R.drawable.poster_shadow_4);//nav_focused_2,poster_shadow_4
		setChildrenDrawingOrderEnabled(true);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (hasFocus()) {
			System.out.println("HomeItemContainer focus : true ");
			super.getDrawingRect(mRect);
			mBound.set(-39+mRect.left, -39+mRect.top, 39+mRect.right, 39+mRect.bottom);
			mDrawable.setBounds(mBound);
			mDrawable.draw(canvas);
		}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
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
