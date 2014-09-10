package com.eastelsoft.tv.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eastelsoft.tv.R;

public class ESItemView extends TextView {

	private Rect mRect;
	private Drawable mDrawable;
	private int mOffset;
	
	public ESItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ESItemView(Context context) {
		super(context);
		init();
	}

	public ESItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		setWillNotDraw(false);
		mRect = new Rect();
		mDrawable = getResources().getDrawable(R.drawable.nav_focused_2);
		mOffset = getResources().getDimensionPixelSize(R.dimen.px44);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		drawBackgroundSelected(canvas);
		super.onDraw(canvas);
	}
	
	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, type);
	}
	
	private void drawBackgroundSelected(Canvas canvas) {
		if (hasFocus()) {
			System.out.println("focus : true");
			CharSequence text = getText();
			if (text != null && text.length() > 2) {
				mDrawable = getResources().getDrawable(R.drawable.nav_focused_3);
			}
			super.getDrawingRect(mRect);
			mRect.left = mOffset;
			mRect.right = mOffset;
			mRect.top = mOffset;
			mRect.bottom = mOffset;
			mDrawable.setBounds(mRect);
			mDrawable.draw(canvas);
		}
	}
	
}
