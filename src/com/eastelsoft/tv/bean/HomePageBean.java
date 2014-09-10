package com.eastelsoft.tv.bean;

import java.util.List;

public class HomePageBean {

	private String status;
	private Results results;
	
	public static class Results {
		public List<NavigatorItem> m1;
		public List<ShowItem> m2;
		public List<ShowItem> m3;
		public List<ShowItem> m4;
		public List<ShowItem> m5;
		public List<NavigatorItem> getM1() {
			return m1;
		}
		public void setM1(List<NavigatorItem> m1) {
			this.m1 = m1;
		}
		public List<ShowItem> getM2() {
			return m2;
		}
		public void setM2(List<ShowItem> m2) {
			this.m2 = m2;
		}
		public List<ShowItem> getM3() {
			return m3;
		}
		public void setM3(List<ShowItem> m3) {
			this.m3 = m3;
		}
		public List<ShowItem> getM4() {
			return m4;
		}
		public void setM4(List<ShowItem> m4) {
			this.m4 = m4;
		}
		public List<ShowItem> getM5() {
			return m5;
		}
		public void setM5(List<ShowItem> m5) {
			this.m5 = m5;
		}
	}
	
	public static class NavigatorItem {
		public String image;
	    public String mtype;
	    public String title;
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getMtype() {
			return mtype;
		}
		public void setMtype(String mtype) {
			this.mtype = mtype;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	public static class ShowItem
	  {
	    public String big_horizontal_image;
	    public String big_vertical_image;
	    public String middle_horizontal_image;
	    public String middle_vertical_image;
	    public String mtype;
	    public String showid;
	    public String title;
	    public String videoid;
		public String getBig_horizontal_image() {
			return big_horizontal_image;
		}
		public void setBig_horizontal_image(String big_horizontal_image) {
			this.big_horizontal_image = big_horizontal_image;
		}
		public String getBig_vertical_image() {
			return big_vertical_image;
		}
		public void setBig_vertical_image(String big_vertical_image) {
			this.big_vertical_image = big_vertical_image;
		}
		public String getMiddle_horizontal_image() {
			return middle_horizontal_image;
		}
		public void setMiddle_horizontal_image(String middle_horizontal_image) {
			this.middle_horizontal_image = middle_horizontal_image;
		}
		public String getMiddle_vertical_image() {
			return middle_vertical_image;
		}
		public void setMiddle_vertical_image(String middle_vertical_image) {
			this.middle_vertical_image = middle_vertical_image;
		}
		public String getMtype() {
			return mtype;
		}
		public void setMtype(String mtype) {
			this.mtype = mtype;
		}
		public String getShowid() {
			return showid;
		}
		public void setShowid(String showid) {
			this.showid = showid;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getVideoid() {
			return videoid;
		}
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}
	  }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}
	
}
