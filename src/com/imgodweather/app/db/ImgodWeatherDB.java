package com.imgodweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.imgodweather.app.model.City;
import com.imgodweather.app.model.County;
import com.imgodweather.app.model.Province;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ImgodWeatherDB {

	/**
	 * 数据库知识复习: 1.写一个类继承SQLiteOpenHelper,在这个类里面实现数据库的创建和升级
	 * 2.使用上面的helper类,得到一个可读或者可写的数据库(getWritableDatabase)
	 */

	public static final String DB_NAME = "imgod_weather";
	public static final int DB_VERSION = 1;
	private static ImgodWeatherDB imgodWeatherDB;
	private SQLiteDatabase sqLiteDatabase;

	// 私有化,单粒模式
	private ImgodWeatherDB(Context context) {
		ImgodWeatherOpenHelper imgodWeatherOpenHelper = new ImgodWeatherOpenHelper(
				context, DB_NAME, null, DB_VERSION);
		sqLiteDatabase = imgodWeatherOpenHelper.getWritableDatabase();
	}

	// 获取ImgodWeatherDB实例
	public synchronized static ImgodWeatherDB getInstance(Context context) {
		if (imgodWeatherDB == null) {
			imgodWeatherDB = new ImgodWeatherDB(context);
		}
		return imgodWeatherDB;
	}

	// 保存省份到省份数据库
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("province_name", province.getProvince_name());
			contentValues.put("province_code", province.getPrivince_code());
			sqLiteDatabase.insert("Province", null, contentValues);
		}
	}

	// 查询数据库,把省份表全部取出来
	public List<Province> loadProvice() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = sqLiteDatabase.query("Province", null, null, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			while (cursor.moveToNext()) {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvince_name(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvince_name(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);
			}
		}
		return list;
	}

	// 保存城市信息到数据库
	public void saveCity(City city) {
		if (city != null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("city_name", city.getCity_name());
			contentValues.put("city_code", city.getCity_code());
			sqLiteDatabase.insert("City", null, contentValues);
		}
	}

	//从数据库中读取某省份的城市列表存储到集合里面
	public List<City> loadCitys(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = sqLiteDatabase.query("City", null,"province_id=?", new String[]{String.valueOf(provinceId)}, null,
				null, null);
		if(cursor.moveToFirst()) {
			while(cursor.moveToNext()) {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
				list.add(city);
			}
		}
		return list;
	}
	
	//保存县的信息到数据库
	public void saveCounty(County county) {
		if(county!=null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("county_name", county.getCounty_name());
			contentValues.put("county_code", county.getCounty_code());
			sqLiteDatabase.insert("County", null, contentValues);
		}
	}
	
	//查询数据库把某城市的县列表给取出来
	public List<County> loadCountys(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = sqLiteDatabase.query("County", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()) {
			while(cursor.moveToNext()){
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCounty_name(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCounty_code(cursor.getString(cursor.getColumnIndex("county_code")));
				list.add(county);
			}
			
		}
		return list;
	}

}
