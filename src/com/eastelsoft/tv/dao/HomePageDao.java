package com.eastelsoft.tv.dao;

import com.eastelsoft.tv.bean.HomePageBean;
import com.eastelsoft.tv.util.http.HttpMethod;
import com.eastelsoft.tv.util.http.HttpUtility;
import com.google.gson.Gson;


public class HomePageDao {

	private String mUrl;
	
	public HomePageDao(String url) {
		mUrl = url;
	}
	
	public String getJSON() throws Exception{
		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Post, mUrl, null);
	}
	
	public HomePageBean getBean() {
		try {
			String jsonString = getJSON();
			Gson gson = new Gson();
			HomePageBean bean = gson.fromJson(jsonString, HomePageBean.class);
			System.out.println("data : "+bean.getStatus());
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
