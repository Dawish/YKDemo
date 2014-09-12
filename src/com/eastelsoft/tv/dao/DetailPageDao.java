package com.eastelsoft.tv.dao;

import java.util.HashMap;

import com.eastelsoft.tv.bean.DetailPageBean;
import com.eastelsoft.tv.util.http.HttpMethod;
import com.eastelsoft.tv.util.http.HttpUtility;
import com.google.gson.Gson;

public class DetailPageDao {

	private String mUrl;
	private HashMap<String, String> mParams;
	
	public DetailPageDao(String url,HashMap<String, String> params) {
		this.mUrl = url;
		this.mParams = params;
	}
	
	public String getJSON() throws Exception {
		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, mUrl, mParams);
	}
	
	public DetailPageBean getBean() {
		try {
			String jsonString = getJSON();
			System.out.println("json : "+jsonString);
			Gson gson = new Gson();
			DetailPageBean bean = gson.fromJson(jsonString, DetailPageBean.class);
			System.out.println("data : "+bean.status);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
