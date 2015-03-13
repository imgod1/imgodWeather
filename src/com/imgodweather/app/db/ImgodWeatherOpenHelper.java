package com.imgodweather.app.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ImgodWeatherOpenHelper extends SQLiteOpenHelper {

	//ʡ�ݱ�Ľ������
	public static final String CREATE_PEOVINCE = "create table Province (id integer primary key autoincrement,provice_name text,privince_code text)";

	//���б�Ľ������
	public static final String CREATE_CITY = "create table City (id integer primary key autoincrement,city_name text,city_code text,province_id integer)";

	//�ر�Ľ������
	public static final String CREATE_COUNTY= "create table County (id integer primary key autoincrement,county_name text,county_code text,city_id integer)";

	public ImgodWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(CREATE_PEOVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
