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
	 * ���ݿ�֪ʶ��ϰ: 1.дһ����̳�SQLiteOpenHelper,�����������ʵ�����ݿ�Ĵ���������
	 * 2.ʹ�������helper��,�õ�һ���ɶ����߿�д�����ݿ�(getWritableDatabase)
	 */

	public static final String DB_NAME = "imgod_weather";
	public static final int DB_VERSION = 1;
	private static ImgodWeatherDB imgodWeatherDB;
	private SQLiteDatabase sqLiteDatabase;

	// ˽�л�,����ģʽ
	private ImgodWeatherDB(Context context) {
		ImgodWeatherOpenHelper imgodWeatherOpenHelper = new ImgodWeatherOpenHelper(
				context, DB_NAME, null, DB_VERSION);
		sqLiteDatabase = imgodWeatherOpenHelper.getWritableDatabase();
	}

	// ��ȡImgodWeatherDBʵ��
	public synchronized static ImgodWeatherDB getInstance(Context context) {
		if (imgodWeatherDB == null) {
			imgodWeatherDB = new ImgodWeatherDB(context);
		}
		return imgodWeatherDB;
	}

	// ����ʡ�ݵ�ʡ�����ݿ�
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("province_name", province.getProvince_name());
			contentValues.put("province_code", province.getPrivince_code());
			sqLiteDatabase.insert("Province", null, contentValues);
		}
	}

	// ��ѯ���ݿ�,��ʡ�ݱ�ȫ��ȡ����
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

	// ���������Ϣ�����ݿ�
	public void saveCity(City city) {
		if (city != null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("city_name", city.getCity_name());
			contentValues.put("city_code", city.getCity_code());
			sqLiteDatabase.insert("City", null, contentValues);
		}
	}

	//�����ݿ��ж�ȡĳʡ�ݵĳ����б�洢����������
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
	
	//�����ص���Ϣ�����ݿ�
	public void saveCounty(County county) {
		if(county!=null) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("county_name", county.getCounty_name());
			contentValues.put("county_code", county.getCounty_code());
			sqLiteDatabase.insert("County", null, contentValues);
		}
	}
	
	//��ѯ���ݿ��ĳ���е����б��ȡ����
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
