package com.eastelsoft.tv.db.task;

import com.eastelsoft.tv.bean.DetailPageBean;
import com.eastelsoft.tv.db.common.DBResult;
import com.eastelsoft.tv.db.common.DatabaseHelper;
import com.eastelsoft.tv.db.table.DetailTable;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DetailDBTask {

	private DetailDBTask() {}
	
	public static SQLiteDatabase getWsd() {
		return DatabaseHelper.getInstance().getWritableDatabase();
	}
	
	public static SQLiteDatabase getRsd() {
		return DatabaseHelper.getInstance().getReadableDatabase();
	}
	
	public static DBResult addOrUpdate(DetailPageBean bean) {
		ContentValues cv = new ContentValues();
		cv.put(DetailTable.showid, bean.detail.showid);
		cv.put(DetailTable.img, bean.detail.img);
		cv.put(DetailTable.title, bean.detail.title);
		cv.put(DetailTable.reputation, bean.detail.reputation);
		cv.put(DetailTable.total_vv, bean.detail.total_vv);
		cv.put(DetailTable.showdate, bean.detail.showdate);
		cv.put(DetailTable.stripe_bottom, bean.detail.stripe_bottom);
		cv.put(DetailTable.desc, bean.detail.desc);
		cv.put(DetailTable.videoid, bean.detail.videoid);
		if (bean.detail.area.size() > 0 && bean.detail.area.size() > 0) {
			cv.put(DetailTable.area, "地区："+bean.detail.area.get(0));
		}else {
			cv.put(DetailTable.area, "地区：未知");
		}
		if (bean.detail.genre.size() > 0 && bean.detail.genre.size() > 0) {
			cv.put(DetailTable.genre, "类型："+bean.detail.genre.get(0));
		}else {
			cv.put(DetailTable.genre, "类型：未知");
		}
		if (bean.detail.director.size() > 0 && bean.detail.director.size() > 0) {
			cv.put(DetailTable.director, "导演："+bean.detail.director.get(0));
		}else {
			cv.put(DetailTable.director, "导演：未知");
		}
		if (bean.detail.performer.size() > 0 && bean.detail.performer.size() > 0) {
			cv.put(DetailTable.performer, "演员："+bean.detail.performer.get(0));
		}else {
			cv.put(DetailTable.performer, "演员：未知");
		}
		
		Cursor c = getWsd().rawQuery("SELECT * FROM "+DetailTable.table_name+" WHERE "+DetailTable.showid+" = ?", 
				new String[]{bean.detail.showid});
		if (c != null && c.getCount() > 0) {
			getWsd().update(DetailTable.table_name, cv, DetailTable.showid+"=?", new String[]{bean.detail.showid});
			return DBResult.update_successfully;
		} else {
			getWsd().insert(DetailTable.table_name, DetailTable.showid, cv);
			return DBResult.add_successfully;
		}
	}
}
