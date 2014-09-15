package com.eastelsoft.tv.bean;

import java.util.ArrayList;

public class DetailRelatedBean {

	public String status;
	public String apptype;
	public String req_id;
	public ArrayList<Result> results;
	
	public static class Result {
		public String id;
		public String showid;
		public String dma;
		public String showname;
		public String show_vthumburl;
		public String videoid;
	}
}
