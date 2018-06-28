package com.demo.textsizechange;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class PreferencesHelper {

	SharedPreferences sp;
	SharedPreferences.Editor editor;
	Context context;

	public PreferencesHelper(Context c, String name) {
		context = c;
		sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void setValue(String key, String value) {
		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}



	public String getValue(String key, String s) {
		return sp.getString(key, s);
	}

	public String getValue(String key) {
		return sp.getString(key, "");
	}

	public void setValue(String key, int value) {
		editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void setValue(String key, long value) {
		editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public int getValueInt(String key) {
		return sp.getInt(key, 0);
	}

	public long getValueLong(String key,long defaultValue) {
		return sp.getLong(key, defaultValue);
	}
	public int getValueInt(String key,int defaultValue){
		return sp.getInt(key,defaultValue);
	}

	public void setValue(String key, boolean value) {
		editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getValueBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public int getSize() {
		if (null == sp.getAll())
			return 0;
		return sp.getAll().size();
	}

	public void remove(String name) {
		editor.remove(name);
		editor.commit();
	}

	public Map<String, ?> getAll() {
		return sp.getAll();

	}

	public void clear() {
		editor.clear();
		editor.commit();
	}

}
