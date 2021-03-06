package edu.gisi.magic.thesismanagement.volley;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by AlexXu on 2016/12/10.
 */

public class CacheTool {

	public static Context context;
	private static SharedPreferences preferences;

	public static void setContext(Context context) {
		CacheTool.context = context;
		preferences = context.getSharedPreferences("CACHE", Activity.MODE_PRIVATE);
	}

	public static void put(String key, String value) {
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String get(String key) {
		return preferences.getString(key, "");
	}

}
