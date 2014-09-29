package com.eastelsoft.tv.dao;

import java.util.HashMap;

import com.eastelsoft.tv.bean.ChannelTopBean;
import com.eastelsoft.tv.util.http.HttpMethod;
import com.eastelsoft.tv.util.http.HttpUtility;
import com.google.gson.Gson;

public class ChannelTopDao {

	private String mUrl;
	private HashMap<String, String> mParams;
	
	public ChannelTopDao(String url,HashMap<String, String> params) {
		this.mUrl = url;
		this.mParams = params;
	}
	
	public String getJSON() throws Exception{
		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, mUrl, mParams);
	}
	
	public ChannelTopBean getBean() {
		try {
			String jsonString = getJSON();
			Gson gson = new Gson();
			ChannelTopBean bean = gson.fromJson(jsonString, ChannelTopBean.class);
			System.out.println("data : "+bean.status);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
