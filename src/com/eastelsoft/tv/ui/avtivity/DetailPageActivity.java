package com.eastelsoft.tv.ui.avtivity;

import java.util.HashMap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.DetailPageBean;
import com.eastelsoft.tv.dao.DetailPageDao;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;
import com.eastelsoft.tv.ui.avtivity.player.VideoPlayerActivity;
import com.eastelsoft.tv.util.URLHelper;
import com.eastelsoft.tv.widget.ESImageView;

public class DetailPageActivity extends BaseActivity {

	private RelativeLayout mFullscreen;
	private RelativeLayout mLoading;
	private LinearLayout mDetailLayout;
	private ESImageView mCenterimage;
	private ESImageView mCenterimage_flag;
	private TextView mTitle;
	private TextView mRating;
	private TextView mPlayCount;
	private TextView mPublishTime;
	private TextView mLastHistory;
	private TextView mArea;
	private TextView mType;
	private TextView mDirector;
	private TextView mActors;
	private TextView mInfoContent;
	private Button mBtnFullDesc;
	private Button mBtnPlay;
	private Button mBtnBuy;
	private Button mBtnShowDrama;
	private Button mBtnFavorite;
	
	private String mShowId;
	private DetailPageBean mBean;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		mShowId = intent.getStringExtra("showid");
		
		setContentView(R.layout.activity_detail_page);
		initViews();
		mFullscreen.setBackgroundResource(R.drawable.bg);
		
		mBtnPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DetailPageActivity.this, VideoPlayerActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new DataAsyncTask().execute("");
	}
	
	private void initViews() {
		mFullscreen = (RelativeLayout)findViewById(R.id.fullscreen);
		mLoading = (RelativeLayout)findViewById(R.id.loading);
		mDetailLayout = (LinearLayout)findViewById(R.id.detailLayout);
		
		mCenterimage = (ESImageView)findViewById(R.id.centerimage);
		mCenterimage_flag = (ESImageView)findViewById(R.id.centerimage_flag);
		
		mTitle = (TextView)findViewById(R.id.title);
		mRating = (TextView)findViewById(R.id.rating);
		mPlayCount = (TextView)findViewById(R.id.textView_detail_movie_vv);
		
		mPublishTime = (TextView)findViewById(R.id.publishTime);
		mLastHistory = (TextView)findViewById(R.id.lastHistory);
		mArea = (TextView)findViewById(R.id.area);
		mType = (TextView)findViewById(R.id.type);
		mDirector = (TextView)findViewById(R.id.director);
		mActors = (TextView)findViewById(R.id.actors);
		mInfoContent = (TextView)findViewById(R.id.infoContent);
		
		mBtnFullDesc = (Button)findViewById(R.id.btnFullDesc);
		mBtnPlay = (Button)findViewById(R.id.btnPlay);
		mBtnBuy = (Button)findViewById(R.id.btnBuy);
		mBtnShowDrama = (Button)findViewById(R.id.btnShowDrama);
		mBtnFavorite = (Button)findViewById(R.id.btnFavorite);
	}
	
	private void updateViews() {
		mLoading.setVisibility(View.INVISIBLE);
		mDetailLayout.setVisibility(View.VISIBLE);
		
		imageLoader.displayImage(mBean.detail.img, mCenterimage, options);
		
		mTitle.setText(mBean.detail.title);
		mRating.setText(mBean.detail.reputation+"分");
		mPlayCount.setText(mBean.detail.total_vv+"次");
		
		mPublishTime.setText("上映："+mBean.detail.showdate);
		mLastHistory.setText("集数："+mBean.detail.stripe_bottom);
		if (mBean.detail.area.size() > 0) {
			mArea.setText("地区："+mBean.detail.area.get(0));
		}else {
			mArea.setText("地区：未知");
		}
		if (mBean.detail.genre != null) {
			mType.setText("类型："+mBean.detail.genre.get(0));
		}else {
			mType.setText("地区：未知");
		}
		if (mBean.detail.director != null) {
			mDirector.setText("导演："+mBean.detail.director.get(0));
		}else {
			mDirector.setText("导演：未知");
		}
		if (mBean.detail.performer != null) {
			mActors.setText("演员："+mBean.detail.performer.get(0));
		}else {
			mActors.setText("演员：未知");
		}
		mInfoContent.setText("简介："+mBean.detail.desc);
	}
	
	private class DataAsyncTask extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			String url = URLHelper.BASE_DETAIL;
			HashMap<String, String> uParams = URLHelper.getPARARMS();
			uParams.put("id", mShowId);
			mBean = new DetailPageDao(url, uParams).getBean();
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			updateViews();
		}
	}
}
