package com.eastelsoft.tv.ui.avtivity;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.DetailDramaBean;
import com.eastelsoft.tv.bean.DetailPageBean;
import com.eastelsoft.tv.bean.DetailRelatedBean;
import com.eastelsoft.tv.dao.DetailDramaDao;
import com.eastelsoft.tv.dao.DetailPageDao;
import com.eastelsoft.tv.dao.DetailRelatedDao;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;
import com.eastelsoft.tv.ui.avtivity.player.VideoPlayerActivity;
import com.eastelsoft.tv.util.URLHelper;
import com.eastelsoft.tv.widget.ESImageView;
import com.eastelsoft.tv.widget.dialog.DramaDialog;

public class DetailPageActivity extends BaseActivity {

	private RelativeLayout mFullscreen;
	private RelativeLayout mLoading;
	private LinearLayout mDetailLayout;
	private LinearLayout mBottomimages;
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
	private ArrayList<ViewGroup> mBottomFramelists;
	private DramaDialog mDramaDialog;
	
	private String mShowId;
	private DetailPageBean mBean;
	private DetailRelatedBean mRelatedBean;
	private DetailDramaBean mDramaBean;
	
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
		
		mBtnShowDrama.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mDramaBean == null) {
					Toast.makeText(DetailPageActivity.this, "剧集数据加载失败！", Toast.LENGTH_SHORT).show();
					return;
				}
				if (mDramaBean.results.size() > 0) {
					mDramaDialog.show();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mBtnPlay.requestFocus();
		new MoiveAsyncTask().execute("");
	}
	
	private void initViews() {
		mFullscreen = (RelativeLayout)findViewById(R.id.fullscreen);
		mLoading = (RelativeLayout)findViewById(R.id.loading);
		mDetailLayout = (LinearLayout)findViewById(R.id.detailLayout);
		mBottomimages = (LinearLayout)findViewById(R.id.bottomimages);
		
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
		
		prepareLayout();
	}
	
	private void prepareLayout() {
		if (mBottomFramelists != null) {
			mBottomFramelists.clear();
			mBottomFramelists = null;
		}
		mBottomFramelists = new ArrayList<>();
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame1));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame2));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame3));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame4));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame5));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame6));
		mBottomFramelists.add((ViewGroup)findViewById(R.id.frame7));
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
		if (mBean.detail.area.size() > 0 && mBean.detail.area.size() > 0) {
			mArea.setText("地区："+mBean.detail.area.get(0));
		}else {
			mArea.setText("地区：未知");
		}
		if (mBean.detail.genre != null && mBean.detail.genre.size() > 0) {
			mType.setText("类型："+mBean.detail.genre.get(0));
		}else {
			mType.setText("地区：未知");
		}
		if (mBean.detail.director != null && mBean.detail.director.size() > 0) {
			mDirector.setText("导演："+mBean.detail.director.get(0));
		}else {
			mDirector.setText("导演：未知");
		}
		if (mBean.detail.performer != null  && mBean.detail.performer.size() > 0) {
			mActors.setText("演员："+mBean.detail.performer.get(0));
		}else {
			mActors.setText("演员：未知");
		}
		mInfoContent.setText("简介："+mBean.detail.desc);
		
		//ask for related moive
		new DramaAsyncTask().execute("");
		new RelatedAsyncTask().execute("");
	}
	
	private void updateRelatedViews() {
		int count = mRelatedBean.results.size();
		if (count > mBottomFramelists.size()) {
			count= mBottomFramelists.size();
		}
		for (int i = 0; i < count; i++) {
			ImageView iv = (ImageView)((ViewGroup)mBottomFramelists.get(i)).findViewById(R.id.ivImage);
			TextView tv = (TextView)((ViewGroup)mBottomFramelists.get(i)).findViewById(R.id.title1);
			tv.setText(mRelatedBean.results.get(i).showname);
			imageLoader.displayImage(mRelatedBean.results.get(i).show_vthumburl, iv, options);
			iv.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					System.out.println("related on click !!!");
				}
			});
		}
	}
	
	private void createDramaDialog() {
		mDramaDialog = new DramaDialog(this, mDramaBean);
		WindowManager.LayoutParams layoutParams = mDramaDialog.getWindow().getAttributes();
		layoutParams.width = getResources().getDimensionPixelSize(R.dimen.px1280);
		layoutParams.height = getResources().getDimensionPixelSize(R.dimen.px720);
		mDramaDialog.getWindow().setAttributes(layoutParams);
	}
	
	private class MoiveAsyncTask extends AsyncTask<String, Integer, Boolean> {
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
	
	private class RelatedAsyncTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String url = URLHelper.BASE_REALTED;
			HashMap<String, String> uParams = URLHelper.getPARARMS();
			uParams.put("id", mShowId);
			uParams.put("apptype", "5");
			uParams.put("module", "1");
			uParams.put("pz", "20");
			mRelatedBean = new DetailRelatedDao(url, uParams).getBean();
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
//			System.out.println("json size related : "+mRelatedBean.results.size());
			if (mRelatedBean == null) {
				Toast.makeText(DetailPageActivity.this, "推荐视频数据加载失败！", Toast.LENGTH_SHORT).show();
				return;
			}
			updateRelatedViews();
		}
	}
	
	private class DramaAsyncTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String url = URLHelper.BASE_DRAMA+mShowId+"/series";
			HashMap<String, String> uParams = URLHelper.getPARARMS();
			mDramaBean = new DetailDramaDao(url, uParams).getBean();
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
//			System.out.println("json size drama : "+mDramaBean.results.size());
			if (mDramaBean == null) {
				Toast.makeText(DetailPageActivity.this, "剧集数据加载失败！", Toast.LENGTH_SHORT).show();
				return;
			}
			createDramaDialog();
		}
	}
	
}
