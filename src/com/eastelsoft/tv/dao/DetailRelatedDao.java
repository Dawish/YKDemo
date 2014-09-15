package com.eastelsoft.tv.dao;

import java.util.HashMap;

import com.eastelsoft.tv.bean.DetailRelatedBean;
import com.eastelsoft.tv.util.http.HttpMethod;
import com.eastelsoft.tv.util.http.HttpUtility;
import com.google.gson.Gson;

public class DetailRelatedDao {

	private String mUrl;
	private HashMap<String, String> mParams;
	
	public DetailRelatedDao(String url,HashMap<String, String> params) {
		this.mUrl = url;
		this.mParams = params;
	}
	
	public String getJSON() throws Exception {
		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, mUrl, mParams);
	}
	
	public DetailRelatedBean getBean() {
		try {
			String jsonString = getJSON();
			System.out.println("json : "+jsonString);
			Gson gson = new Gson();
			DetailRelatedBean bean = gson.fromJson(jsonString, DetailRelatedBean.class);
			System.out.println("data : "+bean.status);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
