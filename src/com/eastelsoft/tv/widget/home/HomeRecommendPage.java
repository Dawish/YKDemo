package com.eastelsoft.tv.widget.home;

import java.util.HashMap;

import com.eastelsoft.tv.ESApplication;
import com.eastelsoft.tv.R;
import com.eastelsoft.tv.bean.HomePageBean;
import com.eastelsoft.tv.bean.HomePageBean.NavigatorItem;
import com.eastelsoft.tv.bean.HomePageBean.ShowItem;
import com.eastelsoft.tv.dao.HomePageDao;
import com.eastelsoft.tv.ui.avtivity.DetailPageActivity;
import com.eastelsoft.tv.ui.avtivity.player.VideoPlayerActivity;
import com.eastelsoft.tv.util.URLHelper;
import com.eastelsoft.tv.widget.ESImageView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class HomeRecommendPage extends HomeBasePage {
	
	private String mTitle;
	private View mContent;
	private HomePageBean bean;
	
	public HomeRecommendPage(Context context,String title) {
		super(context);
		mTitle = title;
	}

	@Override
	public String getPageTitle() {
		return mTitle;
	}

	@Override
	protected View onCreateContentView() {
		mContent = generatePageContent(null, R.layout.home_page_recommend_template, mOffsetX, mOffsetY, true);
		return mContent;
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
	
	protected void initNavItemView(int resId, NavigatorItem item) {
		View view = mContent.findViewById(resId);
		view.setTag(R.id.home_top_bar, item);
		((TextView)view.findViewById(R.id.text)).setText(item.getTitle());
		ESImageView iv = (ESImageView)view.findViewById(R.id.img);
		imageLoader.displayImage(item.getImage(), iv, options);
	}
	
	protected void initRectItemView(int resId, ShowItem item, boolean isVertical) {
		View view = mContent.findViewById(resId);
		view.setTag(R.id.home_top_bar, item);
		((TextView)view.findViewById(R.id.text)).setText(item.getTitle());
		ESImageView iv = (ESImageView)view.findViewById(R.id.img);
		if (isVertical) {
			imageLoader.displayImage(item.getBig_vertical_image(), iv, options);
		} else {
			imageLoader.displayImage(item.getBig_horizontal_image(), iv, options);
		}
	}
	
	private class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String url = URLHelper.HOME_PAGE_URL;
			HashMap<String, String> uParams = URLHelper.getPARARMS();
			HomePageDao dao = new HomePageDao(url, uParams);
			bean = dao.getBean();
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			String status = bean.getStatus();
			if (!"success".equals(status)) {
				Toast.makeText(ESApplication.getInstance(), "数据加载失败，请检查接口!!", Toast.LENGTH_SHORT).show();
				return;
			}
			initData();
		}
	}
	
	public void initData() {
		//m1
		initNavItemView(R.id.navigator_0, bean.getResults().getM1().get(0));
		initNavItemView(R.id.navigator_1, bean.getResults().getM1().get(1));
		initNavItemView(R.id.navigator_2, bean.getResults().getM1().get(2));
		
		//m2
		initRectItemView(R.id.recommend_0, bean.getResults().getM2().get(0), true);
		
		//m3
		initRectItemView(R.id.recommend_1, bean.getResults().getM3().get(0), false);
		initRectItemView(R.id.recommend_2, bean.getResults().getM3().get(1), true);
		initRectItemView(R.id.recommend_3, bean.getResults().getM3().get(2), true);
		
		//m4
		initRectItemView(R.id.recommend_4, bean.getResults().getM4().get(0), false);
		initRectItemView(R.id.recommend_5, bean.getResults().getM4().get(1), true);
		initRectItemView(R.id.recommend_6, bean.getResults().getM4().get(2), true);
		
		//m5
		initRectItemView(R.id.recommend_7, bean.getResults().getM5().get(0), true);
		initRectItemView(R.id.recommend_8, bean.getResults().getM5().get(1), false);
		initRectItemView(R.id.recommend_9, bean.getResults().getM5().get(2), true);
		initRectItemView(R.id.recommend_10, bean.getResults().getM5().get(3), true);
		
		initListener();
	}
	
	public void initListener() {
		NavigatorListener nl = new NavigatorListener();
		mContent.findViewById(R.id.navigator_0).setOnClickListener(nl);
		mContent.findViewById(R.id.navigator_1).setOnClickListener(nl);
		mContent.findViewById(R.id.navigator_2).setOnClickListener(nl);
		
		RectItemListener rl = new RectItemListener();
		mContent.findViewById(R.id.recommend_0).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_1).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_2).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_3).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_4).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_5).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_6).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_7).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_8).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_9).setOnClickListener(rl);
		mContent.findViewById(R.id.recommend_10).setOnClickListener(rl);
	}
	
	private class NavigatorListener implements OnClickListener{
		public void onClick(View v) {
			Object objItem = v.getTag(R.id.home_top_bar);
			NavigatorItem item = (NavigatorItem)objItem;
			switch (v.getId()) {
			case R.id.navigator_0:
				v.setNextFocusUpId(R.id.top_viewpager_navigator);
				Toast.makeText(ESApplication.getInstance(), item.getTitle()+"search click !!!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.navigator_1:
				Toast.makeText(ESApplication.getInstance(), item.getTitle()+"history click !!!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.navigator_2:
				Toast.makeText(ESApplication.getInstance(), item.getTitle()+"favor click !!!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	
	private class RectItemListener implements OnClickListener{
		public void onClick(View v) {
			Object objItem = v.getTag(R.id.home_top_bar);
			ShowItem item = (ShowItem)objItem;
			Intent intent = new Intent();
			intent.setClass(mContext, DetailPageActivity.class);
			intent.putExtra("showid", item.showid);
			mContext.startActivity(intent);
//			Toast.makeText(ESApplication.getInstance(), item.getTitle()+" click !!!", Toast.LENGTH_SHORT).show();
		}
	}
}
