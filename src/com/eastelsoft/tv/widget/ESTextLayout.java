package com.eastelsoft.tv.widget;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class ESTextLayout extends RelativeLayout {
	
	private Paint mPaint;
	private Rect mRect;
	private Rect mBounds;
	private Drawable mDrawable;
	
	private Animation scaleSmallAnimation;
	private Animation scaleBigAnimation;

	public ESTextLayout(Context context) {
		super(context);
		init();
	}

	public ESTextLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ESTextLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.YELLOW);
		mRect = new Rect();
		mBounds = new Rect();
		mDrawable = getResources().getDrawable(R.drawable.poster_foc);
		setWillNotDraw(false);
		setChildrenDrawingOrderEnabled(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (hasFocus()) {
			super.getDrawingRect(mRect);
			mBounds.set(-39+mRect.left, -39+mRect.top, 39+mRect.right, 39+mRect.bottom);
			mDrawable.setBounds(mBounds);
			mDrawable.draw(canvas);
		}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus) {
			bringChildToFront(getFocusedChild());
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
