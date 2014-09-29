package com.eastelsoft.tv.bean;

import java.util.ArrayList;

public class ChannelTopBean {

	public String status;
	public Result results;
	
	public class Result {
		public ArrayList<Top> top;
		public ArrayList<Channel> channel;
	}
	
	public class Top {
		public String mtype;
		public String image;
		public String top_id;
		public String title;
	}
	
	public class Channel {
		public String mtype;
		public String image;
		public String cid;
		public int id;
		public String title;
	}
}
