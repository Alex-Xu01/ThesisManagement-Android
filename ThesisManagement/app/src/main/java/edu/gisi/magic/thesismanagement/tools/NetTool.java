package edu.gisi.magic.thesismanagement.tools;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class NetTool {

	private static Context context;
	private static RequestQueue queue;

	public static void setContext(Context context) {
		NetTool.context = context;
		queue = Volley.newRequestQueue(context);
	}

	public static void post(String url, Listener<String> responseListener, ErrorListener errorListener,
							final Map<String, String> data) {
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return data;
			}
		};
		queue.add(stringRequest);
	}

}
