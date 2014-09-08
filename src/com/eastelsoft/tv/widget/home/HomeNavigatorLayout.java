package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.widget.AutoTextSizeTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeNavigatorLayout extends LinearLayout {

	
	public HomeNavigatorLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HomeNavigatorLayout(Context context) {
		super(context);
	}

	public HomeNavigatorLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOrientation(HORIZONTAL);
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		return super.getChildDrawingOrder(childCount, i);
	}
	
	public static class ItemView extends TextView {

		private Rect mRect = new Rect();
		private Drawable mDrawable = getResources().getDrawable(R.drawable.nav_focused_2);
		private int mOffset = getResources().getDimensionPixelSize(R.dimen.px44);
		
		public ItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			setWillNotDraw(true);
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
				CharSequence text = getText();
				if (text != null && text.length() > 2) {
					mDrawable = getResources().getDrawable(R.drawable.nav_focused_3);
				}
				getDrawingRect(mRect);
				mRect.left = mOffset;
				mRect.right = mOffset;
				mRect.top = mOffset;
				mRect.bottom = mOffset;
				mDrawable.setBounds(mRect);
				mDrawable.draw(canvas);
			}
		}
		
	}
}
