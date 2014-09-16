package com.eastelsoft.tv.widget.dialog;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.DetailDramaBean;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class DramaDialog extends Dialog {
	
	private Context mContext;
	private DetailDramaBean mDramaBean;
	private int pageSize;
	private int pageNum;

	public DramaDialog(Context context,DetailDramaBean bean) {
		super(context,R.style.dialog);
		mContext = context;
		mDramaBean = bean;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_page_dialog_drama);
		setDialogData();
	}
	
	private void setDialogData() {
		LinearLayout mTitleLayout = (LinearLayout)findViewById(R.id.title_layout);
		final LinearLayout mDramArea = (LinearLayout)findViewById(R.id.drama_area);
		mTitleLayout.removeAllViews();
		mDramArea.removeAllViews();
		View.inflate(mContext, R.layout.detail_page_dialog_drama_content, mDramArea);
		
		pageSize = 20;
		pageNum = mDramaBean.results.size()/pageSize + 1;
		for (int i = 0; i < pageNum; i++) {
			TextView tv = new TextView(mContext);
			tv.setText((1+i*pageSize)+"-"+(i+1)*pageSize);
			if (i == (pageNum-1)) {
				int min = Math.min(mDramaBean.results.size(), (i+1)*pageSize);
				tv.setText((1+i*pageSize)+"-"+min);
			}
			tv.setBackgroundResource(R.drawable.detail_dlg_tab);
			tv.setFocusable(true);
			tv.setTextSize(0, mContext.getResources().getDimension(R.dimen.px30));
			tv.setTextColor(mContext.getResources().getColorStateList(R.color.input_keys));
			tv.setGravity(Gravity.CENTER);
			tv.setFocusableInTouchMode(true);
			tv.setClickable(true);
			tv.setTag(i);
			
			LayoutParams layoutParams = new LayoutParams((int)mContext.getResources().getDimension(R.dimen.px200), (int)mContext.getResources().getDimension(R.dimen.px60));
			layoutParams.setMargins(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.px20), 0);
			mTitleLayout.addView(tv, layoutParams);
			
			tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						ViewGroup rootGroup = (ViewGroup)mDramArea.getChildAt(0);
						int count = rootGroup.getChildCount();
						int pageNo = Integer.parseInt(v.getTag().toString());
						for (int j = 0; j < count; j++) {//default count=4
							LinearLayout row = (LinearLayout)rootGroup.getChildAt(j);
							int row_count = row.getChildCount();
							for (int k = 0; k < row_count; k++) {
								TextView tv = (TextView)row.getChildAt(k%5);
								int no = (k+1)+(j*row_count)+pageNo*pageSize;
								if (no < 10) {
									tv.setText("0"+no);
								}else{
									tv.setText(""+no);
								}
								if (no > mDramaBean.results.size()) {
									tv.setVisibility(View.INVISIBLE);
								} else {
									tv.setVisibility(View.VISIBLE);
								}
							}
						}
					}
				}
			});
		}
		
	}
}
