package com.eastelsoft.tv.bean;

import java.util.ArrayList;

public class DetailPageBean {

	public String status;
	public Detail detail;

	public static class Detail {
		public String accountLocally = "";
		public ArrayList<String> anime_genre;
		public ArrayList<String> area;
		public ArrayList<Audiolang> audiolang;
		public String cats;
		public int completed;
		public String desc;
		public ArrayList<String> director;
		public int duration;
		public String episode_total;
		public String firstepisode_videoid;
		public ArrayList<String> genre;
		public ArrayList<String> host;
		public String img;
		public String img_default;
		public String lastepisode_videoid;
		public int limit;
		public ArrayList<String> original;
		public int paid;
		public ArrayList<String> performer;
		public String pk_odshow;
		public ArrayList<String> production;
		public int publicType;
		public String reputation;
		public int show_videostage;
		public String show_videotype;
		public String showdate;
		public String showid;
		public ArrayList<String> station;
		public String stripe_bottom;
		public ArrayList<String> teacher;
		public String title;
		public String total_comment;
		public String total_fav;
		public String total_vv;
		public ArrayList<String> variety_genre;
		public String videoid;
		public ArrayList<String> voice;
	}

	public static class Audiolang {
		public String lang;
	}
}
