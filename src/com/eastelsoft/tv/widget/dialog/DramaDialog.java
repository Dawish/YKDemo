package com.eastelsoft.tv.widget.dialog;

import java.util.ArrayList;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.DetailDramaBean;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DramaDialog extends Dialog {
	
	private Context mContext;
	private DetailDramaBean mDramaBean;
	private int pageSize;
	private int pageNum;

	public DramaDialog(Context context,DetailDramaBean bean) {
		super(context);
		mContext = context;
		mDramaBean = bean;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_page_dialog_drama);
	}
	
	private void setDialogData() {
		LinearLayout mTitleLayout = (LinearLayout)findViewById(R.id.title_layout);
		LinearLayout mDramArea = (LinearLayout)findViewById(R.id.drama_area);
		mTitleLayout.removeAllViews();
		mDramArea.removeAllViews();
		View.inflate(mContext, R.layout.detail_page_dialog_drama_content, mDramArea);
		
		pageSize = 15;
		pageNum = (int)(Math.ceil(mDramaBean.results.size()/pageSize));
		for (int i = 0; i < pageNum; i++) {
			
		}
		
	}
}
