package com.eastelsoft.tv.bean;

import java.util.ArrayList;

public class DetailDramaBean {
	
	public int completed;
	public ArrayList<result> results;
	public String status;
	public int total;

	public class result {
		public boolean hasHistory;
		public String img;
		public String img_hd;
		public int index;
		public String pubdate;
		public int show_videoseq;
		public String show_videostage;
		public int stg;
		public String title;
		public String video_date;
		public int video_stage;
		public String videoid;

		public result() {
		}
	}
}
