package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class HomeItemContainer extends RelativeLayout {
	
	private Rect mBound;
	private Drawable mDrawable;
	private boolean mDrawShadow;
	private Paint mPaint;
	private Rect mRect;

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
		mDrawable = getResources().getDrawable(R.drawable.poster_shadow_4);
		mPaint = new Paint();
		mPaint.setColor(Color.GRAY);
		setChildrenDrawingOrderEnabled(true);
	}

	@Override
	public void buildDrawingCache(boolean autoScale) {
		super.buildDrawingCache(autoScale);
	}

	@Override
	public void buildLayer() {
		super.buildLayer();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	public Bitmap getDrawingCache() {
		return super.getDrawingCache();
	}

	@Override
	public void getDrawingRect(Rect outRect) {
		super.getDrawingRect(outRect);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mDrawShadow = true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (!hasFocus()) {
			return;
		}
		if (mDrawShadow && hasFocus()) {
			super.getDrawingRect(mRect);
			mBound.set(-39+mRect.left, -39+mRect.top, 39+mRect.right, 39+mRect.bottom);
			mDrawable.setBounds(mBound);
			mDrawable.draw(canvas);
		}
		super.onDraw(canvas);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
}
