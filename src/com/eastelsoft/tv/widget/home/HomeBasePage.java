package com.eastelsoft.tv.widget.home;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.widget.ESHScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public abstract class HomeBasePage implements IViewPagerContent{

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions options;
	protected View mContent;
	protected Context mContext;
	protected int mOffsetX;
	protected int mOffsetY;
	
	public HomeBasePage(Context context) {
		mContext = context;
		mOffsetX = (int)mContext.getResources().getDimension(R.dimen.px72);
		mOffsetY = (int)mContext.getResources().getDimension(R.dimen.px98);
		options = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
	}

	@Override
	public View createContentView() {
		mContent = onCreateContentView();
		updateContentView();
		return mContent;
	}

	protected View generatePageContent(ViewGroup viewGroup, int resId, int offsetX, int offsetY, boolean flag) {
		ViewGroup mViewGroup = (ViewGroup)View.inflate(mContext, resId, null);
		ViewGroup container = generatePageContentContainer(offsetX, flag);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.leftMargin = offsetX;
		layoutParams.rightMargin = offsetX;
		layoutParams.topMargin = offsetY;
		layoutParams.bottomMargin = offsetY;
		container.addView(mViewGroup, layoutParams);
		return container;
	}
	
	private ViewGroup generatePageContentContainer(int offsetX, boolean flag) {
		ESHScrollView scrollView = new ESHScrollView(mContext);
		scrollView.setClipChildren(false);
		scrollView.setClipToPadding(false);
		return scrollView;
	}
	
	protected abstract View onCreateContentView();

}