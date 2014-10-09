package com.eastelsoft.tv.db.common;

import com.eastelsoft.tv.ESApplication;
import com.eastelsoft.tv.db.table.DetailTable;
import com.eastelsoft.tv.db.table.FavoriteTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static DatabaseHelper instance = null;
	private static String DB_NAME = "yktv.db";
	private static int DB_VERSION = 1;
	
	static final String CREATE_DETAIL_TABLE_SQL = "create table " + DetailTable.table_name
            + "("
            + DetailTable.id + " integer primary key autoincrement,"
            + DetailTable.showid + " text,"
            + DetailTable.img + " text,"
            + DetailTable.title + " text,"
            + DetailTable.reputation + " text,"
            + DetailTable.total_vv + " text,"
            + DetailTable.showdate + " text"
            + DetailTable.stripe_bottom + " text,"
            + DetailTable.area + " text"
            + DetailTable.genre + " text,"
            + DetailTable.director + " text"
            + DetailTable.performer + " text,"
            + DetailTable.desc + " text"
            + DetailTable.videoid + " text,"
            + ");";
	
	static final String CREATE_FAVORITE_TABLE_SQL = "create table " + FavoriteTable.table_name
            + "("
            + FavoriteTable.id + " integer primary key autoincrement,"
            + FavoriteTable.showid + " text,"
            + FavoriteTable.img + " text,"
            + FavoriteTable.title + " text,"
            + FavoriteTable.stripe_bottom + " text,"
            + ");";
	
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
		db.execSQL(CREATE_DETAIL_TABLE_SQL);
		db.execSQL(CREATE_FAVORITE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
