package com.eastelsoft.tv.db.common;

import com.eastelsoft.tv.ESApplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static DatabaseHelper instance = null;
	private static String DB_NAME = "weibo.db";
	private static int DB_VERSION = 2;
	
	public static synchronized DatabaseHelper getInstance() {
		if (instance == null) {
			instance = new DatabaseHelper(ESApplication.getInstance());
		}
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
