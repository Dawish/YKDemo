package com.eastelsoft.tv.widget.home;

import java.util.HashMap;
import java.util.Map;

import com.eastelsoft.tv.ESApplication;
import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.ChannelTopBean;
import com.eastelsoft.tv.dao.ChannelTopDao;
import com.eastelsoft.tv.util.URLHelper;
import com.eastelsoft.tv.widget.ESImageView;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeChannelPage extends HomeBasePage {

	private String mTitle;
	private View mContent;
	private ChannelTopBean bean;
	
	public HomeChannelPage(Context context,String title) {
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
		mContent = generatePageContent(null, R.layout.home_page_channel_template, mOffsetX, mOffsetY, true);
		return mContent;
	}

	private void initItemView(int resId, ChannelTopBean.Channel channel) {
		View view = mContent.findViewById(resId);
		ESImageView imageView = (ESImageView)view.findViewById(R.id.img);
		TextView textView = (TextView)view.findViewById(R.id.text);
		imageLoader.displayImage(channel.image, imageView, options);
		textView.setText(channel.title);
	}
	
	private void initData() {
		initItemView(R.id.channel_0, bean.results.channel.get(0));
		initItemView(R.id.channel_1, bean.results.channel.get(1));
		initItemView(R.id.channel_2, bean.results.channel.get(2));
		initItemView(R.id.channel_3, bean.results.channel.get(3));
		initItemView(R.id.channel_4, bean.results.channel.get(4));
		initItemView(R.id.channel_5, bean.results.channel.get(5));
		initItemView(R.id.channel_6, bean.results.channel.get(6));
		initItemView(R.id.channel_7, bean.results.channel.get(7));
		initItemView(R.id.channel_8, bean.results.channel.get(8));
		initItemView(R.id.channel_9, bean.results.channel.get(9));
		
//		mContent.findViewById(R.id.channel_0).requestFocus();
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
