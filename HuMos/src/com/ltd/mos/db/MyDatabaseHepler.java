package com.ltd.mos.db;

import com.ltd.mos.util.Const;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabaseHepler extends ToSDCardSQLiteOpenHelper {
	public static final String LOCALPATH = "/humos/data/db/";
	private static final String DB_NAME = "humos.db";
	private static final int DB_VERSION = 1;

	public MyDatabaseHepler(Context context) {
		super(context, LOCALPATH, DB_NAME, null, DB_VERSION);
	}

	// ¹ºÎï³µ±í
	private static final String CREATE_SHOPCAR_TABLE = "CREATE TABLE "
			+ Const.TABLE_SHOPCAR 
			+ "("
			+ "goods_id		INTEGER			PRIMARY KEY   AUTOINCREMENT,"
			+ "goods_number VARCHAR(20),"
			+ "goods_title VARCHAR(50),"
			+ "goods_price VARCHAR(20),"
			+ "goods_url VARCHAR(50),"
			+ "goods_num VARCHAR(20)" 
			+ ")";

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_SHOPCAR_TABLE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
