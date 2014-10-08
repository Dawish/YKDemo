package com.eastelsoft.tv.widget.home;

import java.util.HashMap;

import com.eastelsoft.tv.ESApplication;
import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.ChannelTopBean;
import com.eastelsoft.tv.dao.ChannelTopDao;
import com.eastelsoft.tv.util.URLHelper;
import com.eastelsoft.tv.widget.ESImageView;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeTopPage extends HomeBasePage {

	private String mTitle;
	private View mContent;
	private ChannelTopBean bean;
	
	public HomeTopPage(Context context, String title) {
		super(context);
		mTitle = title;
	}

	@Override
	public String getPageTitle() {
		return mTitle;
	}

	@Override
	public void updateContentView() {
		if (bean == null) {
			MyAsyncTask task = new MyAsyncTask();
			task.execute("");
		} else {
			initData();
		}
	}

	@Override
	protected View onCreateContentView() {
		mContent = generatePageContent(null, R.layout.home_page_top_template, mOffsetX, mOffsetY, true);
		return mContent;
	}
	
	private void initItemView(int resId, ChannelTopBean.Top top) {
		View view = mContent.findViewById(resId);
		ESImageView img = (ESImageView)view.findViewById(R.id.img);
		imageLoader.displayImage(top.image, img, options);
		((TextView)view.findViewById(R.id.text)).setText(top.title);
		ImageView icon = (ImageView)view.findViewById(R.id.icon);
		String top_id = top.top_id;
		if ("1".equals(top_id)) {
			icon.setImageDrawable(mContent.getResources().getDrawable(R.drawable.ranking_reco_icon));
		}else if("2".equals(top_id)) {
			icon.setImageDrawable(mContent.getResources().getDrawable(R.drawable.ranking_search_icon));
		}else if("3".equals(top_id)) {
			icon.setImageDrawable(mContent.getResources().getDrawable(R.drawable.ranking_cinemas_icon));
		}
	}
	
	private void initData() {
		initItemView(R.id.top_0, bean.results.top.get(0));
		initItemView(R.id.top_1, bean.results.top.get(1));
		initItemView(R.id.top_2, bean.results.top.get(2));
	}
	
	public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String url = URLHelper.HOME_CHANNEL_TOP_URL;
			HashMap<String, String> uParams = URLHelper.getPARARMS();
			ChannelTopDao dao = new ChannelTopDao(url, uParams);
			bean = dao.getBean();
			return true; 
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			String status = bean.status;
			if (!"success".equals(status)) {
				Toast.makeText(ESApplication.getInstance(), "数据加载失败，请检查接口!!", Toast.LENGTH_SHORT).show();
				return;
			}
			initData();
		}
		
	}

}
