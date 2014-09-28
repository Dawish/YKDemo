package com.eastelsoft.tv.util;

import java.util.HashMap;

public class URLHelper {
	
	public static String BASE_PARAMS = "pid=b2b26ec8ed9bbc9f"
			+ "&guid=d9868782f88e5063f8ce1dc031c180c3"
			+ "&ngdid=bd4b8cc87e6428c306ca78de6a430b83"
			+ "&ver=2.4.0"
			+ "&operator=CMCC_46000"
			+ "&network=WIFI";
	
	public static String HOME_PAGE_URL = "http://tv.api.3g.youku.com/tv/main"; 
	
	public static String HOME_CHANNEL_TOP_URL = "http://tv.api.3g.youku.com/tv/main/top"; 

	public static String BASE_DETAIL = "http://tv.api.3g.youku.com/layout/smarttv/play/detail";

	public static String BASE_REALTED = "http://tv.api.3g.youku.com/common/shows/relate";
	
	public static String BASE_DRAMA = "http://tv.api.3g.youku.com/layout/smarttv/shows/";
	
	public static HashMap<String, String> getPARARMS() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("pid", "b2b26ec8ed9bbc9f");
		params.put("guid", "d9868782f88e5063f8ce1dc031c180c3");
		params.put("ngdid", "bd4b8cc87e6428c306ca78de6a430b83");
		params.put("ver", "2.4.0");
		params.put("ngdid", "bd4b8cc87e6428c306ca78de6a430b83");
		params.put("operator", "CMCC_46000");
		params.put("network", "WIFI");
		return params;
	} 
}
